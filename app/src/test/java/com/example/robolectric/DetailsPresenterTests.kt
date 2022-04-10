package com.example.robolectric

import com.example.robolectric.presenter.presenter.details.DetailsPresenter
import com.example.robolectric.view.details.ViewDetailsContract
import com.example.robolectric.view.search.ViewSearchContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

private lateinit var detailsPresenter: DetailsPresenter

@Mock
private lateinit var viewContract: ViewDetailsContract

@Before
fun setUp() {
    MockitoAnnotations.initMocks(this)
    detailsPresenter.onAttach(viewContract)
}

@Test
fun setCounter_Test() {
    detailsPresenter.setCounter(1)
    detailsPresenter.onIncrement()
    verify(viewContract, times(1)).setCount(2)
}

@Test
fun onIncrement_Test() {
    detailsPresenter.onIncrement()
    verify(viewContract, times(1)).setCount(1)
}

@Test
fun onDecrement_Test() {
    detailsPresenter.onDecrement()
    verify(viewContract, times(1)).setCount(-1)
}
@Test
fun onAttach() {
    detailsPresenter.onAttach(viewContract)
    detailsPresenter.onIncrement()
    verify(viewContract, times(1)).setCount(1)
}
@Test
fun onDetach() {
    detailsPresenter.onDetach()
    detailsPresenter.onIncrement()
    verify(viewContract, times(0)).setCount(1)
}

