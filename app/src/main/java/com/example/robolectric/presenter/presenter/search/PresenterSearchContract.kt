package com.example.robolectric.presenter.presenter.search


import com.example.robolectric.presenter.presenter.PresenterContract

internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
}