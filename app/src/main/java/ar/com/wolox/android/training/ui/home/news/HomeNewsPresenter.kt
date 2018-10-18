package ar.com.wolox.android.training.ui.home.news

import ar.com.wolox.android.training.model.INewsService
import ar.com.wolox.android.training.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject
import kotlin.math.min

class HomeNewsPresenter @Inject constructor(private val vRetrofitServices: RetrofitServices)
    : BasePresenter<IHomeNewsView>() {

    fun loadNews(pageIndex: Int, newsPerPage: Int){
        val service = vRetrofitServices.getService(INewsService::class.java)
        val call = service?.getAllNews("email")

        view.progressCircleVisibilityOn()
        call?.enqueue(networkCallback {
            onResponseSuccessful {
                runIfViewAttached { view ->
                    val fromIndex = (pageIndex-1)*newsPerPage
                    val toIndex = min(pageIndex*newsPerPage,it!!.size)

                    if (fromIndex <= toIndex){
                        view.onNewsFound(it.copyOfRange(fromIndex,toIndex).toMutableList())
                    } else {
                        noMoreNewsError()
                    }
                }
                view.progressCircleVisibilityOff()
            }

            onResponseFailed { _, _ -> runIfViewAttached(Runnable {
                view.onJsonError()
                view.progressCircleVisibilityOff()
            }) }

            onCallFailure { runIfViewAttached(Runnable {
                noMoreNewsError()
                view.progressCircleVisibilityOff()
            }) }
        })
    }

    private fun noMoreNewsError(){
        view.onNewsUpdateError()
        view.progressCircleVisibilityOff()
    }
}