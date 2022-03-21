package com.example.robolectric.presenter.presenter.details

import com.example.robolectric.presenter.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {
    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}