package ar.com.wolox.android.training.ui.home.news.detail

import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsDetailActivity : WolmoActivity() {

    companion object {
        private const val intentExtra = "newsDetailMessage"
    }

    override fun layout(): Int = R.layout.activity_base

    override fun init() {
        val bundle = Bundle()
        bundle.putSerializable(intentExtra,intent.getSerializableExtra(intentExtra))
        val newsDetailFragment = NewsDetailFragment()
        newsDetailFragment.arguments = bundle
        replaceFragment(R.id.vActivityBaseContent, newsDetailFragment)
    }
}
