package com.waheed.nytimes.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.waheed.nytimes.R
import com.waheed.nytimes.other.Constants
import com.waheed.nytimes.ui.viewmodels.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_nyt.*
import javax.inject.Inject

@AndroidEntryPoint
class NYTFragment @Inject constructor() : Fragment(R.layout.fragment_nyt) {

    lateinit var viewModel: ArticlesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)

        listeners()

        handleBackNavigation()
    }

    /**
     * Button Events
     */
    private fun listeners() {
        btnSearchArticles.setOnClickListener {
            navigateToSearchArticles()
        }
        btnMostViewed.setOnClickListener {
            navigateToArticlesFragment(Constants.MOST_VIEWED)
        }
        btnMostShared.setOnClickListener {
            navigateToArticlesFragment(Constants.MOST_SHARED)
        }
        btnMostEmailed.setOnClickListener {
            navigateToArticlesFragment(Constants.MOST_EMAILED)
        }
    }

    /**
     * Handle Back Navigation For Fragment
     */
    private fun handleBackNavigation() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    /**
     * Launch Popular Articles Fragment
     */
    private fun navigateToArticlesFragment(type: String) {
        viewModel.getPopularArticles(type)
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.action_nytFragment_to_articlesFragment)
    }

    /**
     * Launch Search Articles Fragment
     */
    private fun navigateToSearchArticles() {
        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.action_nytFragment_to_searchArticleFragment)
    }
}




















