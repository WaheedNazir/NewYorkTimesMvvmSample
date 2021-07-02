package com.waheed.nytimes.repositories

import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.other.Resource

interface ArticlesRepository {

    suspend fun getPopularArticle(type: String): Resource<PopularArticlesResponse>

    suspend fun searchForArticle(query: String): Resource<ArticleSearchResponse>
}