package ar.com.wolox.android.training.ui.home.news

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.android.R
import ar.com.wolox.android.training.model.News
import ar.com.wolox.android.training.ui.home.news.detail.NewsDetailMessage
import kotlinx.android.synthetic.main.fragment_home_news_item.view.*
import org.greenrobot.eventbus.EventBus
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import javax.inject.Inject



class HomeNewsAdapter @Inject constructor(private val sharedPreferences: SharedPreferences,
                                          private var newsList: MutableList<News>,
                                          private val context: Context)
    : RecyclerView.Adapter<HomeNewsAdapter.HomeNewsViewHolder>() {

    companion object {
        private const val userIdKey = "UserId"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsViewHolder {
        return HomeNewsViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_home_news_item, parent, false))
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: HomeNewsViewHolder, position: Int) {
        val prettyTime = PrettyTime()
        val simpleDateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern)

        holder.newsTitle.text = newsList[position].title

        holder.newsText.text = newsList[position].text

        holder.newsPicture.setImageURI(newsList[position].pictureString.replace("http://","https://"))

        holder.newsDate.text = prettyTime.format(simpleDateFormat.parse(newsList[position].date))

        if(newsList[position].likes.contains(sharedPreferences.getInt(userIdKey,-1))){
            holder.newsLike.setImageResource(R.drawable.ic_like_on)
            holder.newsLike.tag = R.drawable.ic_like_on
        } else {
            holder.newsLike.setImageResource(R.drawable.ic_like_off)
            holder.newsLike.tag = R.drawable.ic_like_off
        }

        holder.itemView.setOnClickListener {
            EventBus.getDefault().post(NewsDetailMessage(newsList[position]))
        }
    }

    fun addNews(news: List<News>){
        newsList.addAll(news)
    }

    class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val newsTitle = itemView.vNewsTextViewTitle!!
        val newsText = itemView.vNewsTextViewText!!
        val newsPicture = itemView.vNewsPictureView!!
        val newsDate = itemView.vNewsTextViewDate!!
        val newsLike = itemView.vNewsLikeView!!

    }
}