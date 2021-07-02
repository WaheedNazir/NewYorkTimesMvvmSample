package com.waheed.nytimes.repositories

import androidx.lifecycle.MutableLiveData
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.other.Resource

class FakeArticlesRepositoryAndroidTest : ArticlesRepository {

    /**
     *
     */
    private val searchedArticles = mutableListOf<ArticleSearchResponse.Response.Doc>()
    private val observableSearchedArticles =
        MutableLiveData<List<ArticleSearchResponse.Response.Doc>>(searchedArticles)


    /**
     *
     */
    private val popularArticles = mutableListOf<PopularArticlesResponse.Article>()
    private val observablePopularArticles =
        MutableLiveData<List<PopularArticlesResponse.Article>>(popularArticles)


    /**
     *
     */
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    /**
     *
     */
    override suspend fun getPopularArticle(type: String): Resource<PopularArticlesResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(PopularArticlesResponse())
        }
    }

    /**
     *
     */
    override suspend fun searchForArticle(query: String): Resource<ArticleSearchResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ArticleSearchResponse(response = ArticleSearchResponse.Response(docs = emptyList())))
        }
    }
}











