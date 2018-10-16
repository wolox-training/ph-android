package ar.com.wolox.android.training.ui.home.news

import ar.com.wolox.android.training.model.News

interface IHomeNewsView {

    fun onNewsFound(news: Array<News>)

    fun onJsonError()

    fun onNewsUpdateError()

    fun progressCircleVisibilityOn()

    fun progressCircleVisibilityOff()
}