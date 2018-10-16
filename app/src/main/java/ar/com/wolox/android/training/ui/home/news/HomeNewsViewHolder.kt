package ar.com.wolox.android.training.ui.home.news

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.fragment_home_news_item.view.*

class HomeNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val newsTitle = itemView.vNewsTextViewTitle!!
    val newsText = itemView.vNewsTextViewText!!
    val newsPicture = itemView.vNewsPictureView!!
    val newsDate = itemView.vNewsTextViewDate!!
    val newsLike = itemView.vNewsLikeView!!

}
