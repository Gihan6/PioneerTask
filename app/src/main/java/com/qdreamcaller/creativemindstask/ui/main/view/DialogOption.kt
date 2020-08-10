package com.qdreamcaller.creativemindstask.ui.main.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.qdreamcaller.creativemindstask.R
import com.qdreamcaller.creativemindstask.model.Repo

class DialogOption(private val repo: Repo) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_option, null, false)
        setupView(view)
        return view
    }

    private fun setupView(view: View) {
        val repoUrl = view.findViewById(R.id.tv_repoUrl) as TextView
        val ownerUrl = view.findViewById(R.id.tv_ownerUrl) as TextView
        val cancel = view.findViewById(R.id.btn_close) as Button

        repoUrl.setOnClickListener {
            openBrowser(repo.url)
        }
        ownerUrl.setOnClickListener {
            openBrowser(repo.ownerUrl)
        }
        cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun openBrowser(url: String) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(url)
        startActivity(openURL)

    }

}
