package com.example.robolectric.presenter.presenter

import com.example.robolectric.view.ViewContract

internal interface PresenterContract {
    fun onAttach(viewContract: ViewContract)
    fun onDetach()
}