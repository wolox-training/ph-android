package ar.com.wolox.android.training.ui.home.news


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.training.BaseConfiguration
import ar.com.wolox.android.training.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.melnykov.fab.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home_news.*
import javax.inject.Inject

class HomeNewsFragment @Inject constructor() : WolmoFragment<HomeNewsPresenter>(), IHomeNewsView {

    val title = "NEWS"

    private lateinit var newsList : RecyclerView
    private lateinit var fab : FloatingActionButton

    override fun layout(): Int = R.layout.fragment_home_news

    override fun init() {
        presenter.loadNews()
    }

    override fun onNewsFound(news: Array<News>){
        newsList = vHomeNewsRecyclerView
        fab = vFloatingActionButton

        newsList.layoutManager = LinearLayoutManager(context)
        newsList.adapter = HomeNewsAdapter(context!!.getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME,0),news, context!!)
        fab.attachToRecyclerView(newsList)
    }

    override fun onJsonError() {
        Toast.makeText(activity?.applicationContext, R.string.login_error_json_connection, Toast.LENGTH_LONG).show()
    }

    override fun onNewsUpdateError(){
        Toast.makeText(activity?.applicationContext, R.string.login_error_news_update, Toast.LENGTH_LONG).show()
    }

    override fun progressCircleVisibilityOn() {
        vNewsProgressCircle.visibility = View.VISIBLE
    }

    override fun progressCircleVisibilityOff() {
        vNewsProgressCircle.visibility = View.GONE
    }

}
