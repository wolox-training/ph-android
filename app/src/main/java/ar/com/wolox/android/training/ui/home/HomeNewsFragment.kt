package ar.com.wolox.android.training.ui.home


import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class HomeNewsFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    val title = "NEWS"

    override fun layout(): Int = R.layout.fragment_home_news

    override fun init() {

    }

}
