package ar.com.wolox.android.training.ui.home.news


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_home_news.*
import javax.inject.Inject

class HomeNewsFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    val title = "NEWS"

    private lateinit var newsList : RecyclerView
    private var news:MutableList<String> = ArrayList()

    override fun layout(): Int = R.layout.fragment_home_news

    override fun init() {
        news.add("¿Famosos y sólo amigos?")
        news.add("Hipnosis: la nueva vedette de las neurociencias")
        news.add("Como cuidar los muebles de cuero")
        news.add("¿Famosos y sólo amigos?")
        news.add("Hipnosis: la nueva vedette de las neurociencias")
        news.add("Como cuidar los muebles de cuero")
        news.add("¿Famosos y sólo amigos?")
        news.add("Hipnosis: la nueva vedette de las neurociencias")
        news.add("Como cuidar los muebles de cuero")
        news.add("¿Famosos y sólo amigos?")
        news.add("Hipnosis: la nueva vedette de las neurociencias")
        news.add("Como cuidar los muebles de cuero")

        newsList = vHomeNewsRecyclerView
        newsList.layoutManager = LinearLayoutManager(context)
        newsList.adapter = HomeNewsAdapter(news, context!!)
    }

}
