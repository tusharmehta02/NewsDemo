package com.assignment.doubtnut.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.doubtnut.R
import com.assignment.doubtnut.adapter.NewsListAdapter
import com.assignment.doubtnut.model.NewsResponse
import com.assignment.doubtnut.viewmodel.NewsViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class NewsListActivity : DaggerAppCompatActivity(), NewsListAdapter.ItemClickListener {

    private lateinit var newsViewModel: NewsViewModel

    private lateinit var newsListAdapter: NewsListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        initToolbar()

        setUpRecycler()

        initObservers()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_news_list)
    }


    private fun initObservers() {
        newsViewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)

        newsViewModel.resultLiveData.observe(this, getResultObserver())

        val result = newsViewModel.resultLiveData.value
        if (result != null)
            newsListAdapter.setNews(result)
        else
            newsViewModel.loadNews()

    }

    private fun getResultObserver(): Observer<List<NewsResponse.Article>> {
        return Observer {
            if (it.isNotEmpty())
                newsListAdapter.setNews(it)
        }
    }

    private fun setUpRecycler() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            newsListAdapter =
                NewsListAdapter(this@NewsListActivity)
            adapter = newsListAdapter
        }
    }

    override fun onItemClick(item: NewsResponse.Article, sharedElement: AppCompatImageView) {
        val intent = NewsDetailActivity.getStartIntent(this, item)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            sharedElement, getString(R.string.transition_name)
        )
        startActivity(intent, options.toBundle())
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
}
