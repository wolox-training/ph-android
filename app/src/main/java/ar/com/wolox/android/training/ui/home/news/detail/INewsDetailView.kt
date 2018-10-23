package ar.com.wolox.android.training.ui.home.news.detail

import ar.com.wolox.android.training.model.News

interface INewsDetailView {

    fun loadNewsData(newsPost: News)

    fun onJsonError()
}
