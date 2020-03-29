package com.assignment.doubtnut.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.assignment.doubtnut.R
import com.assignment.doubtnut.model.NewsResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val article = intent.getParcelableExtra<NewsResponse.Article>(EXTRAS_ARTICLE)

        initToolbar()

        setViewData(article)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_news_detail)
    }

    private fun setViewData(article: NewsResponse.Article) {
        Glide.with(this).load(article.urlToImage).error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher).into(iv_news_image)

        article.author?.let {
            tv_author.text = "- $it"
            tv_author.visibility = View.VISIBLE
        }

        tv_content.text = article.content
        if (article.description != null) {
            tv_description.text = article.description
            tv_description.visibility = View.VISIBLE
        } else
            tv_description.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    companion object {

        private const val EXTRAS_ARTICLE = "extras_article"

        fun getStartIntent(context: Context, article: NewsResponse.Article): Intent {
            return Intent(
                context,
                NewsDetailActivity::class.java
            ).also { it.putExtra(EXTRAS_ARTICLE, article) }
        }
    }
}
