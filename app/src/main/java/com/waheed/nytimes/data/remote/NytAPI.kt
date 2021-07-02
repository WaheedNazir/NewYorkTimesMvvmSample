package com.waheed.nytimes.data.remote

import com.waheed.nytimes.BuildConfig
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.other.Constants.PERIOD
import com.waheed.nytimes.other.Constants.POPULAR_ARTICLES_END_POINT
import com.waheed.nytimes.other.Constants.SEARCH_ARTICLES_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NytAPI {

    @GET(POPULAR_ARTICLES_END_POINT)
    suspend fun getPopularArticles(
        @Path("type") type: String,
        @Path("period") period: String = PERIOD,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<PopularArticlesResponse>


    @GET(SEARCH_ARTICLES_END_POINT)
    suspend fun searchForArticle(
        @Query("q") searchQuery: String,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<ArticleSearchResponse>
}