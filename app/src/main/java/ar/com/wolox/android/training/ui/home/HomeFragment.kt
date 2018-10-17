package ar.com.wolox.android.training.ui.home

import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.training.ui.home.news.HomeNewsFragment
import ar.com.wolox.android.training.ui.home.profile.HomeProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_home_main.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    @Inject internal lateinit var newsFragment: HomeNewsFragment
    @Inject internal lateinit var profileFragment: HomeProfileFragment
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_home_main

    override fun init() {
        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(newsFragment, newsFragment.title),
                Pair<Fragment, String>(profileFragment, profileFragment.title))
        vViewPager.adapter = fragmentPagerAdapter
        vTabLayout.setupWithViewPager(vViewPager)
        setTabIcons()
    }

    private fun setTabIcons(){
        vTabLayout.getTabAt(0)?.setIcon(R.drawable.tab_selector_news)
        vTabLayout.getTabAt(1)?.setIcon(R.drawable.tab_selector_profile)
    }
}
