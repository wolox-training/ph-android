package ar.com.wolox.android.training.ui.home.news

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ar.com.wolox.android.R
import ar.com.wolox.android.training.utils.onClickListener
import org.ocpsoft.prettytime.PrettyTime
import javax.inject.Inject

class HomeNewsAdapter @Inject constructor(private val newsList: List<HomeNews>, private val context: Context) : RecyclerView.Adapter<HomeNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_home_news_item, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        var prettyTime = PrettyTime()

        holder.newsTitle.text = newsList[position].title
        holder.newsText.text = newsList[position].text
        holder.newsPicture.setImageURI(newsList[position].pictureString)
        holder.newsDate.text = prettyTime.format(newsList[position].date)
        holder.newsLike.setImageResource(R.drawable.ic_like_off)
        holder.newsLike.tag = R.drawable.ic_like_off

        holder.newsLike.onClickListener {
            if(holder.newsLike.tag == R.drawable.ic_like_off) {
                holder.newsLike.setImageResource(R.drawable.ic_like_on)
                holder.newsLike.tag = R.drawable.ic_like_on
            } else if(holder.newsLike.tag == R.drawable.ic_like_on) {
                holder.newsLike.setImageResource(R.drawable.ic_like_off)
                holder.newsLike.tag = R.drawable.ic_like_off
            }
        }
    }
}