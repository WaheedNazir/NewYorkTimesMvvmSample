package com.waheed.nytimes.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.waheed.nytimes.adapters.SearchArticlesAdapter
import com.waheed.nytimes.adapters.ArticlesAdapter
import com.waheed.nytimes.repositories.FakeArticlesRepositoryAndroidTest
import com.waheed.nytimes.ui.articles.ArticlesFragment
import com.waheed.nytimes.ui.main.NYTFragment
import com.waheed.nytimes.ui.search.SearchArticlesFragment
import com.waheed.nytimes.ui.viewmodels.ArticlesViewModel
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val searchArticlesAdapter: SearchArticlesAdapter,
    private val articlesAdapter: ArticlesAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SearchArticlesFragment::class.java.name -> SearchArticlesFragment(searchArticlesAdapter)
            NYTFragment::class.java.name -> NYTFragment()
            ArticlesFragment::class.java.name -> ArticlesFragment(
                articlesAdapter,
                ArticlesViewModel(FakeArticlesRepositoryAndroidTest())
            )
            else -> super.instantiate(classLoader, className)
        }
    }
}