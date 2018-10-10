package ar.com.wolox.android.training.ui.home.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ar.com.wolox.android.R
import javax.inject.Inject

class HomeNewsAdapter @Inject constructor(private val newsList: List<String>, private val context: Context) : RecyclerView.Adapter<HomeNewsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_home_news_item, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        holder.news.text = newsList[position]
    }

}