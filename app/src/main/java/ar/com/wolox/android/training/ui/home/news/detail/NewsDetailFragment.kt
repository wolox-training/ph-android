package ar.com.wolox.android.training.ui.home.news.detail


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.training.BaseConfiguration
import ar.com.wolox.android.training.model.News
import ar.com.wolox.android.training.ui.home.news.HomeNewsAdapter
import ar.com.wolox.android.training.ui.home.news.HomeNewsPresenter
import ar.com.wolox.android.training.ui.home.news.IHomeNewsView
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import com.melnykov.fab.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home_news.*
import javax.inject.Inject



class NewsDetailFragment @Inject constructor(private val post: News) : WolmoFragment<BasePresenter<Any>>(), INewsDetailView {

    override fun layout(): Int = R.layout.fragment_home_news_detail

    override fun init() {
        loadElements()
    }

    fun loadElements(){

    }

    override fun setListeners() {
        super.setListeners()
    }

}
