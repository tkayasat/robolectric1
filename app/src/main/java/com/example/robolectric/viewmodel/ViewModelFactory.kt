package com.example.robolectric.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.robolectric.model.SchedulerProvider
import com.example.robolectric.presenter.presenter.RepositoryContract
import com.example.robolectric.viewmodel.search.SearchViewModel

class ViewModelFactory constructor(
    private val repository: RepositoryContract,
    private val appSchedulerProvider: SchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchViewModel(repository, appSchedulerProvider) as T
}