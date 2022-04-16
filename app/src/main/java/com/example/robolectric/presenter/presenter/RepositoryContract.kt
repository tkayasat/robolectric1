package com.example.robolectric.presenter.presenter

import com.example.robolectric.repository.RepositoryCallback

internal interface RepositoryContract {
    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )
}