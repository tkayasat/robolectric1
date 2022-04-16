package com.example.robolectric.repository

import com.example.robolectric.model.SearchResponse
import retrofit2.Response

interface RepositoryCallback {
    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()
}