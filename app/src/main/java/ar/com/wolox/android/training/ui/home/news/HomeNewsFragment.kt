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

    companion object {
        private var pageIndex = 1
        private var isLastPage = false
        private var isLoading = false
        private const val newsPerPage = 2
    }

    private lateinit var newsList : RecyclerView
    private lateinit var newsListAdapter: HomeNewsAdapter
    private lateinit var newsListLayoutManager: LinearLayoutManager
    private lateinit var fab : FloatingActionButton

    override fun layout(): Int = R.layout.fragment_home_news

    override fun init() {
        presenter.loadNews(pageIndex, newsPerPage)
        newsList = vHomeNewsRecyclerView
        fab = vFloatingActionButton
    }

    override fun setListeners() {
        super.setListeners()
        newsList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = newsListLayoutManager.childCount
                val totalItemCount = newsListLayoutManager.itemCount
                val firstVisibleItemPosition = newsListLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        presenter.loadNews(pageIndex, newsPerPage)
                    }
                }
            }
        })
        vHomeNewsPullToRefresh.setOnRefreshListener {
            pageIndex = 1
            isLastPage = false
            vHomeNewsPullToRefresh.isRefreshing = false
            presenter.loadNews(pageIndex, newsPerPage)
        }
    }

    override fun onNewsFound(news: MutableList<News>){
        if (pageIndex == 1){
            news.addAll(news)   // 4
            news.addAll(news)   // 8
            news.addAll(news)   // 16
            news.addAll(news)   // 32
            newsListLayoutManager = LinearLayoutManager(context)
            newsList.layoutManager = newsListLayoutManager
            newsListAdapter = HomeNewsAdapter(context!!.getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME,0),news, context!!)
            newsList.adapter = newsListAdapter
            newsListAdapter.notifyDataSetChanged()
            fab.attachToRecyclerView(newsList)
        } else {
            newsListAdapter.addNews(news)
            newsListAdapter.notifyItemRangeInserted((pageIndex-1)*newsPerPage+31, newsPerPage)
        }

        ++pageIndex
    }

    override fun onJsonError() {
        Toast.makeText(activity?.applicationContext, R.string.login_error_json_connection, Toast.LENGTH_LONG).show()
    }

    override fun onNewsUpdateError(){
        isLastPage = true
        Toast.makeText(activity?.applicationContext, R.string.login_error_news_update, Toast.LENGTH_LONG).show()
    }

    override fun progressCircleVisibilityOn() {
        isLoading = true
        vNewsProgressCircle.visibility = View.VISIBLE
    }

    override fun progressCircleVisibilityOff() {
        isLoading = false
        vNewsProgressCircle.visibility = View.GONE
    }

}
