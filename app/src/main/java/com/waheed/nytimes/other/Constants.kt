package com.waheed.nytimes.other

object Constants {

    //End Points
    const val POPULAR_ARTICLES_END_POINT = "mostpopular/v2/{type}/{period}.json"
    const val SEARCH_ARTICLES_END_POINT = "search/v2/articlesearch.json"

    const val PERIOD = "30" // 1, 7 , 30 Days

    const val SEARCH_TIME_DELAY = 300L

    const val MOST_VIEWED = "viewed"
    const val MOST_EMAILED = "emailed"
    const val MOST_SHARED = "shared"

    const val NO_INTERNET = "No internet connection"
    const val SOMETHING_WENT_WRONG = "An unknown error occurred"
}