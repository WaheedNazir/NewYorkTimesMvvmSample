package com.waheed.nytimes.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.waheed.nytimes.R
import com.waheed.nytimes.adapters.SearchArticlesAdapter
import com.waheed.nytimes.other.Constants.SEARCH_TIME_DELAY
import com.waheed.nytimes.other.Status
import com.waheed.nytimes.ui.viewmodels.ArticlesViewModel
import com.waheed.nytimes.utils.KeyboardUtils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search_article.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchArticlesFragment @Inject constructor(
    val searchArticlesAdapter: SearchArticlesAdapter
) : Fragment(R.layout.fragment_search_article) {

    lateinit var viewModel: ArticlesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)
        setupRecyclerView()
        subscribeToObservers()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchForArticles(editable.toString())
                    }
                }
            }
        }

        etSearch.requestFocus()
        KeyboardUtils.showKeyboard(requireContext())

        searchArticlesAdapter.setOnItemClickListener {
            findNavController().popBackStack()
        }
    }

    private fun subscribeToObservers() {
        viewModel.searchedArticles.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val articles = result.data?.response?.docs
                        searchArticlesAdapter.articles = articles ?: listOf()
                        progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            requireActivity().rootLayout,
                            result.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupRecyclerView() {
        rvSearchedArticles.apply {
            adapter = searchArticlesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}