package com.qdreamcaller.creativemindstask.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.qdreamcaller.creativemindstask.R
import com.qdreamcaller.creativemindstask.model.Repo
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val repos: ArrayList<Repo>, private val listener: ListenerAdapter) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(repo: Repo) {
            itemView.apply {
                repoName.text = repo.name
                repoOwner.text = repo.login
                repoDesc.text = repo.description
//                if (repo.fork) {
//                    container.setBackgroundColor(
//                        ContextCompat.getColor(
//                            container.context,
//                            R.color.white
//                        )
//                    )
//                } else {
//                    container.setBackgroundColor(
//                        ContextCompat.getColor(
//                            container.context,
//                            R.color.colorAccent
//                        )
//                    )
//
//                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = repos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(repos[position])
        holder.itemView.setOnLongClickListener {
            listener.onLongClick(repos[position])
            false
        }
    }

    fun addRepos(repoList: List<Repo>) {
        this.repos.apply {
            addAll(repoList)
        }

    }

}