package com.waheed.nytimes.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.waheed.nytimes.adapters.SearchArticlesAdapter
import com.waheed.nytimes.adapters.ArticlesAdapter
import com.waheed.nytimes.ui.articles.ArticlesFragment
import com.waheed.nytimes.ui.search.SearchArticlesFragment
import javax.inject.Inject

class NYTFragmentFactory @Inject constructor(
    private val searchArticlesAdapter: SearchArticlesAdapter,
    private val articlesAdapter: ArticlesAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            SearchArticlesFragment::class.java.name -> SearchArticlesFragment(searchArticlesAdapter)
            NYTFragment::class.java.name -> NYTFragment()
            ArticlesFragment::class.java.name -> ArticlesFragment(articlesAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}