package com.example.robolectric.presenter.presenter.search

import com.example.robolectric.model.SearchResponse
import com.example.robolectric.presenter.presenter.RepositoryContract
import com.example.robolectric.repository.RepositoryCallback
import com.example.robolectric.view.ViewContract
import com.example.robolectric.view.search.ViewSearchContract
import retrofit2.Response

internal class SearchPresenter internal constructor(
    private var viewContract: ViewSearchContract
) : PresenterSearchContract, RepositoryCallback {

    override fun searchGitHub(searchQuery: String) {
        viewContract.displayLoading(true)
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContract.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContract.displayError("Search results or total count are null")
            }
        } else {
            viewContract.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        viewContract.displayLoading(false)
        viewContract.displayError()
    }
    override fun onAttach(viewContract: ViewContract) {
        this.viewContract = viewContract as ViewSearchContract
    }

    override fun onDetach() {

    }
}