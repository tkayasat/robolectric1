package com.example.robolectric

import com.example.robolectric.presenter.presenter.search.SearchPresenter
import com.example.robolectric.repository.GitHubRepository
import com.example.robolectric.view.search.ViewSearchContract
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTests {

    private lateinit var searchPresenter: SearchPresenter

    @Mock
    private lateinit var viewContract: ViewSearchContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchPresenter.onAttach(viewContract)
    }


    @Test
    fun onDetach() {
        searchPresenter.onDetach()
        searchPresenter.handleGitHubError()
        Mockito.verify(viewContract, Mockito.times(0)).displayError()
    }

    @Test
    fun onAttach() {
        searchPresenter.onDetach()
        searchPresenter.onAttach(viewContract)
        searchPresenter.handleGitHubError()
        Mockito.verify(viewContract, Mockito.times(1)).displayError()
    }
}