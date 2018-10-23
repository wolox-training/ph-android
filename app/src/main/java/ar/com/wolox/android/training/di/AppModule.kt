package ar.com.wolox.android.training.di

import ar.com.wolox.android.training.ui.home.HomeActivity
import ar.com.wolox.android.training.ui.home.HomeFragment
import ar.com.wolox.android.training.ui.home.news.HomeNewsFragment
import ar.com.wolox.android.training.ui.home.news.detail.NewsDetailActivity
import ar.com.wolox.android.training.ui.home.news.detail.NewsDetailFragment
import ar.com.wolox.android.training.ui.home.profile.HomeProfileFragment
import ar.com.wolox.android.training.ui.login.LoginActivity
import ar.com.wolox.android.training.ui.login.LoginFragment
import ar.com.wolox.android.training.ui.root.RootActivity
import ar.com.wolox.android.training.ui.root.RootFragment
import ar.com.wolox.android.training.ui.signup.SignupActivity
import ar.com.wolox.android.training.ui.signup.SignupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector
    internal abstract fun rootActivity(): RootActivity

    @ContributesAndroidInjector
    internal abstract fun rootFragment(): RootFragment

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun homeNewsFragment(): HomeNewsFragment

    @ContributesAndroidInjector
    internal abstract fun homeProfileFragment(): HomeProfileFragment

    @ContributesAndroidInjector
    internal abstract fun signupActivity(): SignupActivity

    @ContributesAndroidInjector
    internal abstract fun signupFragment(): SignupFragment

    @ContributesAndroidInjector
    internal abstract fun newsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun newsDetailFragment(): NewsDetailFragment
}