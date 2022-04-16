package com.example.robolectric.repository

import com.example.robolectric.model.SearchResponse
import com.example.robolectric.presenter.presenter.RepositoryContract
import retrofit2.Response

internal class FakeGitHubRepository : RepositoryContract {

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback,
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(42, listOf())))
    }
}
