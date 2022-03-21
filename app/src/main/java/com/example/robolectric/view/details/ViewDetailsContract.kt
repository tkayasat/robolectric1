package com.example.robolectric.view.details

import com.example.robolectric.view.ViewContract

internal interface ViewDetailsContract : ViewContract {
    fun setCount(count: Int)
}
