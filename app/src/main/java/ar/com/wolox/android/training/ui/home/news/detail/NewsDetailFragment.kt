package ar.com.wolox.android.training.ui.home.news.detail


import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.training.BaseConfiguration
import ar.com.wolox.android.training.model.News
import ar.com.wolox.android.training.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home_news_detail.*
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import javax.inject.Inject


class NewsDetailFragment @Inject constructor() : WolmoFragment<NewsDetailPresenter>(), INewsDetailView {

    companion object {
        private const val userIdKey = "UserId"
        private const val intentExtra = "newsDetailMessage"
        private var newsPostId = 0
    }

    override fun layout(): Int = R.layout.fragment_home_news_detail

    override fun init() {
        val newsPost = arguments?.getSerializable(intentExtra) as News

        newsPostId = newsPost.newsId
        loadNewsData(newsPost)
    }

    override fun loadNewsData(newsPost: News){
        vNewsDetailTitle.text = newsPost.title

        vNewsDetailContent.text = newsPost.text

        val simpleDateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern)
        vNewsDetailDate.text = PrettyTime().format(simpleDateFormat.parse(newsPost.date))

        vNewsDetailPictureView.setImageURI(newsPost.pictureString.replace("http://","https://"))

        if(newsPost.likes.contains(context!!.getSharedPreferences(BaseConfiguration.SHARED_PREFERENCES_NAME,0)
                        .getInt(userIdKey,-1))){
            vNewsDetailLike.setImageResource(R.drawable.ic_like_on)
            vNewsDetailLike.tag = R.drawable.ic_like_on
        } else {
            vNewsDetailLike.setImageResource(R.drawable.ic_like_off)
            vNewsDetailLike.tag = R.drawable.ic_like_off
        }
    }

    override fun onJsonError() {
        Toast.makeText(activity?.applicationContext, R.string.login_error_json_connection, Toast.LENGTH_LONG).show()
    }

    override fun setListeners() {
        super.setListeners()
        vNewsDetailBack.onClickListener {
            activity?.onBackPressed()
        }
        vNewsDetailLike.onClickListener {
            if(vNewsDetailLike.tag == R.drawable.ic_like_off) {
                vNewsDetailLike.setImageResource(R.drawable.ic_like_on)
                vNewsDetailLike.tag = R.drawable.ic_like_on
            } else if(vNewsDetailLike.tag == R.drawable.ic_like_on) {
                vNewsDetailLike.setImageResource(R.drawable.ic_like_off)
                vNewsDetailLike.tag = R.drawable.ic_like_off
            }
        }
        vNewsDetailPullToRefresh.setOnRefreshListener {
            presenter.reloadNewsPost(newsPostId)
            vNewsDetailPullToRefresh.isRefreshing = false
        }
    }
}
