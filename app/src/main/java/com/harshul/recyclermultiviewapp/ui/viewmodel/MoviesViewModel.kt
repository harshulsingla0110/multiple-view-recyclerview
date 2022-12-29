package com.harshul.recyclermultiviewapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harshul.recyclermultiviewapp.data.models.HomeRecyclerViewItem
import com.harshul.recyclermultiviewapp.data.repos.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<List<HomeRecyclerViewItem>>()
    val homeListItemsLiveData: LiveData<List<HomeRecyclerViewItem>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

     private fun getHomeListItems() = viewModelScope.launch {

        val moviesDeferred = async { MoviesRepository.getMovies() }
        val directorsDeferred = async { MoviesRepository.getDirectors() }

        val movies = moviesDeferred.await()
        val directors = directorsDeferred.await()

        val homeItemsList = mutableListOf<HomeRecyclerViewItem>()
        if (movies.isSuccessful && directors.isSuccessful) {
            homeItemsList.add(HomeRecyclerViewItem.Title(1, "Recommended Movies"))
            movies.body()?.let { homeItemsList.addAll(it) }
            homeItemsList.add(HomeRecyclerViewItem.Title(2, "Top Directors"))
            directors.body()?.let { homeItemsList.addAll(it) }
            _homeListItemsLiveData.postValue(homeItemsList)
        }
    }
}