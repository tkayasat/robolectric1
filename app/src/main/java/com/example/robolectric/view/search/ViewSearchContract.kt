package com.example.robolectric.view.search

import com.example.robolectric.model.SearchResult
import com.example.robolectric.presenter.presenter.search.SearchPresenter
import com.example.robolectric.view.ViewContract

internal interface ViewSearchContract : ViewContract {
    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
    abstract fun SearchPresenter(viewContract: MainActivity): SearchPresenter
}
