package com.waheed.nytimes.repositories

import androidx.lifecycle.MutableLiveData
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.other.Resource

class FakeArticlesRepository : ArticlesRepository {

    private val searchedArticles = mutableListOf<ArticleSearchResponse.Response.Doc>()
    private val observableShoppingItems =
        MutableLiveData<List<ArticleSearchResponse.Response.Doc>>(searchedArticles)


    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    /**
     * Get Popular Article
     */
    override suspend fun getPopularArticle(type: String): Resource<PopularArticlesResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)

        } else {
            val response = PopularArticlesResponse(
                articles = listOf(PopularArticlesResponse.Article(abstract = type))
            )
            Resource.success(response)
        }
    }

    /**
     * Search Article
     */
    override suspend fun searchForArticle(query: String): Resource<ArticleSearchResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)

        } else {
            val response = ArticleSearchResponse.Response(
                docs = listOf(ArticleSearchResponse.Response.Doc(abstract = query))
            )
            val articleResponse = ArticleSearchResponse(response = response)
            Resource.success(articleResponse)
        }
    }
}











