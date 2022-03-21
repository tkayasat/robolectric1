package com.example.robolectric.presenter.presenter.search


import com.example.robolectric.model.SearchResponse
import com.example.robolectric.repository.GitHubRepository
import com.example.robolectric.view.ViewContract
import com.example.robolectric.view.search.ViewSearchContract
import retrofit2.Response


internal abstract class SearchPresenter internal constructor(
    //private var viewContract: ViewSearchContract,
    private val repository: GitHubRepository,
) : PresenterSearchContract, GitHubRepository.GitHubRepositoryCallback {

    private var viewContract: ViewSearchContract? = null

    override fun searchGitHub(searchQuery: String) {
        with(viewContract) { this?.displayLoading(true) }
        repository.searchGithub(searchQuery, this)
    }

    override fun handleGitHubResponse(response: Response<SearchResponse?>?) {
        viewContract?.displayLoading(false)
        if (response != null && response.isSuccessful) {
            val searchResponse = response.body()
            val searchResults = searchResponse?.searchResults
            val totalCount = searchResponse?.totalCount
            if (searchResults != null && totalCount != null) {
                viewContract?.displaySearchResults(
                    searchResults,
                    totalCount
                )
            } else {
                viewContract?.displayError("Search results or total count are null")
            }
        } else {
            viewContract?.displayError("Response is null or unsuccessful")
        }
    }

    override fun handleGitHubError() {
        val displayLoading = viewContract?.displayLoading(false)
        viewContract?.displayError()
    }

    override fun onAttach(viewContract: ViewContract) {
        this.viewContract = viewContract as ViewSearchContract
    }

    override fun onDetach() {
        viewContract = null
    }
}