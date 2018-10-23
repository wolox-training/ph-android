package ar.com.wolox.android.training.ui.home.news.detail

import ar.com.wolox.android.training.model.INewsService
import ar.com.wolox.android.training.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class NewsDetailPresenter @Inject constructor(private val vRetrofitServices: RetrofitServices)
    : BasePresenter<INewsDetailView>() {

    fun reloadNewsPost(newsPostId : Int){
        val service = vRetrofitServices.getService(INewsService::class.java)
        val call = service?.getNewsPostById(newsPostId)

        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { _ ->
                    view.loadNewsData(it!![0])
                }
            }

            onCallFailure { runIfViewAttached(Runnable {
                view.onJsonError()
            }) }
        })
    }
}