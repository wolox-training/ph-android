package ar.com.wolox.android.training.ui.home.news

import ar.com.wolox.android.training.model.News

interface IHomeNewsView {

    fun onNewsFound(news: MutableList<News>)

    fun onCallFailed()

    fun onCallRequested()

    fun onNoNewsAvailable()

    fun progressCircleVisibilityOn()

    fun progressCircleVisibilityOff()
}