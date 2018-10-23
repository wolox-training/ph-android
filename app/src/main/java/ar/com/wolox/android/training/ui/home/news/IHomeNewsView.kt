package ar.com.wolox.android.training.ui.home.news

import ar.com.wolox.android.training.model.News
import ar.com.wolox.android.training.ui.home.news.detail.NewsDetailMessage

interface IHomeNewsView {

    fun onNewsFound(news: MutableList<News>)

    fun onCallFailed()

    fun onCallRequested()

    fun onNoNewsAvailable()

    fun progressCircleVisibilityOn()

    fun progressCircleVisibilityOff()

    fun onNewsDetailMessage(newsDetailMessage: NewsDetailMessage)
}