package com.example.robolectric.presenter.presenter

import com.example.robolectric.model.SearchResponse
import com.example.robolectric.repository.RepositoryCallback
import io.reactivex.Observable

interface RepositoryContract {

    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

     fun searchGithub(
         query: String
     ): Observable<SearchResponse>

     suspend fun searchGithubAsync(
         query: String
     ): SearchResponse
 }