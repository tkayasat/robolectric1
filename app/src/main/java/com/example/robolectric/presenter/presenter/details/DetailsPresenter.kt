package com.example.robolectric.presenter.presenter.details

import com.example.robolectric.view.ViewContract
import com.example.robolectric.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    private var count: Int = 0
) : PresenterDetailsContract {

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract.setCount(count)
    }

    override fun onAttach(viewContract: ViewContract) {
    }

    override fun onDetach() {
    }
}