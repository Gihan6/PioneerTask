package com.qdreamcaller.creativemindstask.ui.main.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.qdreamcaller.creativemindstask.R
import com.qdreamcaller.creativemindstask.model.Repo
import com.qdreamcaller.creativemindstask.ui.main.adapter.ListenerAdapter
import com.qdreamcaller.creativemindstask.ui.main.adapter.MainAdapter
import com.qdreamcaller.creativemindstask.ui.main.viewModel.MainViewModel
import com.qdreamcaller.creativemindstask.util.Status
import com.qdreamcaller.creativemindstask.util.scheduler.NotificationWorker
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


// SwipeRefreshLayout.OnRefreshListener
class MainActivity : AppCompatActivity(), ListenerAdapter {

    private val viewModel by inject<MainViewModel>()
    private lateinit var adapter: MainAdapter
    var currentPage = 0
    var noData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        initListenerForViewModel()
        getDataFromServer(currentPage)
        schedulerWork()
        search()
    }

    private fun search() {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                filterFromLocal(et_search.text.toString())
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })
    }

    private fun filterFromLocal(keyWord: String) {
        if (keyWord.isEmpty().not()) {
            currentPage = -1
            initAdapter()
            viewModel.filterDataFromLocal(keyWord)
        }
    }

    private fun schedulerWork() {
        val mWorkManager = WorkManager.getInstance()

        val myConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
            .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
            .build()

        val mRequest = PeriodicWorkRequest.Builder(
            NotificationWorker::class.java, 1,TimeUnit.HOURS).setConstraints(myConstraints).build()

        mWorkManager.enqueueUniquePeriodicWork(
            "workName",
            ExistingPeriodicWorkPolicy.REPLACE,
            mRequest
        )

        mWorkManager.getWorkInfoByIdLiveData(mRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null) {
                    val state = workInfo.state
                    if (state.ordinal == 1) {
                        Toast.makeText(this, "Scheduler Refresh", Toast.LENGTH_SHORT).show()
                        refresh()

                    }


                }
            })

    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

    }

    private fun refresh() {
        initAdapter()
        currentPage = 0
        noData = false
        getDataFromServer(currentPage)
    }

    private fun afterRefresh() {
        swipeRefresh.isRefreshing = false
    }

    private fun setupUI() {
        initAdapter()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    if (!noData)//to check if have more data or not
                        getDataFromServer(currentPage)
                }
            }
        })
        swipeRefresh.setOnRefreshListener {
            refresh()
            Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show()
            afterRefresh()

        }

    }

    private fun getDataFromServer(page: Int) {
        progressBar.visibility = View.VISIBLE
        viewModel.getReposFromWebServices(page)

    }

    private fun retrieveList(repos: List<Repo>) {
        adapter.apply {
            if (repos.isNotEmpty()) {
                addRepos(repos)
                notifyDataSetChanged()
            } else
                noData = true
        }
    }

    override fun onLongClick(position: Repo) {
        showDialog(position)
    }

    private fun showDialog(repo: Repo) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_option)
        val repoUrl = dialog.findViewById(R.id.tv_repoUrl) as TextView
        val ownerUrl = dialog.findViewById(R.id.tv_ownerUrl) as TextView
        val cancel = dialog.findViewById(R.id.btn_close) as Button

        repoUrl.setOnClickListener {
            openBrowser(repo.url)
        }
        ownerUrl.setOnClickListener {
            openBrowser(repo.ownerUrl)
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun openBrowser(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)

    }

    private fun initListenerForViewModel() {
        viewModel.getRepos().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        if (currentPage >= 0)//else that mean in search mode
                            currentPage++
                        resource.data?.let { repos -> retrieveList(repos) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}
