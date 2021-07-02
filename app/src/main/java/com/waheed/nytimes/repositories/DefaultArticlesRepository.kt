package com.waheed.nytimes.repositories

import com.waheed.nytimes.data.remote.NytAPI
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.other.Constants.NO_INTERNET
import com.waheed.nytimes.other.Constants.SOMETHING_WENT_WRONG
import com.waheed.nytimes.other.Resource
import javax.inject.Inject

class DefaultArticlesRepository @Inject constructor(private val nytAPI: NytAPI) :
    ArticlesRepository {

    /**
     * Popular Articles
     */
    override suspend fun getPopularArticle(type: String): Resource<PopularArticlesResponse> {
        return try {
            val response = nytAPI.getPopularArticles(type)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(SOMETHING_WENT_WRONG, null)
            } else {
                Resource.error(SOMETHING_WENT_WRONG, null)
            }
        } catch (e: Exception) {
            Resource.error(NO_INTERNET, null)
        }
    }

    /**
     * Search Articles
     */
    override suspend fun searchForArticle(query: String): Resource<ArticleSearchResponse> {
        return try {
            val response = nytAPI.searchForArticle(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(SOMETHING_WENT_WRONG, null)
            } else {
                Resource.error(SOMETHING_WENT_WRONG, null)
            }
        } catch (e: Exception) {
            Resource.error(NO_INTERNET, null)
        }
    }
}














