package ar.com.wolox.android.training.ui.home.news

import ar.com.wolox.android.training.model.INewsService
import ar.com.wolox.android.training.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class HomeNewsPresenter @Inject constructor(private val vRetrofitServices: RetrofitServices)
    : BasePresenter<IHomeNewsView>() {

    fun loadNews(){
        val service = vRetrofitServices.getService(INewsService::class.java)
        val call = service?.getAllNews()

        view.progressCircleVisibilityOn()
        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { view ->
                    view.onNewsFound(it!!)
                }
                view.progressCircleVisibilityOff()
            }

            onResponseFailed { _, _ -> runIfViewAttached(Runnable {
                view.onJsonError()
                view.progressCircleVisibilityOff()
            }) }

            onCallFailure { runIfViewAttached(Runnable {
                view.onNewsUpdateError()
                view.progressCircleVisibilityOff()
            }) }
        })
    }
}