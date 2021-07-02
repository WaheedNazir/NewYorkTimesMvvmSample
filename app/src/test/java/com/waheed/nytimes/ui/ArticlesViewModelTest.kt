package com.waheed.nytimes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.waheed.nytimes.MainCoroutineRule
import com.waheed.nytimes.other.Constants
import com.waheed.nytimes.other.Status
import com.waheed.nytimes.repositories.FakeArticlesRepository
import com.waheed.nytimes.ui.viewmodels.ArticlesViewModel
import com.google.common.truth.Truth.assertThat
import com.waheed.nytimes.data.remote.responses.ArticleSearchResponse
import com.waheed.nytimes.data.remote.responses.PopularArticlesResponse
import com.waheed.nytimes.getOrAwaitValueTest
import com.waheed.nytimes.other.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setup() {
        viewModel = ArticlesViewModel(FakeArticlesRepository())
    }

    @Test
    fun `insert popular articles with loading state, returns success`() {
        viewModel.addPopularArticles(Resource(Status.LOADING, PopularArticlesResponse(), ""))
        val value = viewModel.popularArticles.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.LOADING)
    }

    @Test
    fun `insert popular articles with invalid response, returns error`() {
        viewModel.addPopularArticles(Resource(Status.ERROR, null, ""))
        val value = viewModel.popularArticles.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert popular articles with valid response, returns success`() {
        val response = Resource(Status.SUCCESS, PopularArticlesResponse(), "")
        viewModel.addPopularArticles(response)

        val value = viewModel.popularArticles.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `insert popular articles with valid response and verify data, returns error`() {
        val articles = ArrayList<PopularArticlesResponse.Article>()
        articles.add(PopularArticlesResponse.Article(abstract = "Test Abstract"))
        val response = Resource(Status.SUCCESS, PopularArticlesResponse(articles = articles), "")
        viewModel.addPopularArticles(response)

        val value = viewModel.popularArticles.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.data?.articles?.first()?.abstract).isNotEqualTo("Test")
    }

    @Test
    fun `insert popular articles with valid response and verify data, returns success`() {
        val articles = ArrayList<PopularArticlesResponse.Article>()
        articles.add(PopularArticlesResponse.Article(abstract = "Test Abstract"))
        val response = Resource(Status.SUCCESS, PopularArticlesResponse(articles = articles), "")
        viewModel.addPopularArticles(response)

        val value = viewModel.popularArticles.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.data?.articles?.first()?.abstract).isEqualTo("Test Abstract")
    }

    @Test
    fun `search for article with valid query, returns success`() {
        viewModel.searchForArticles("Test Abstract")
        val value = viewModel.searchedArticles.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.data?.response?.docs?.first()?.abstract).isEqualTo(
            "Test Abstract"
        )
    }

    @Test
    fun `Get most viewed popular article with, returns success`() {
        viewModel.getPopularArticles("viewed")
        val value = viewModel.popularArticles.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.data?.articles?.first()?.abstract).isEqualTo(
            "viewed"
        )
    }
}













