package com.harshul.recyclermultiviewapp.data.api

import com.harshul.recyclermultiviewapp.data.models.HomeRecyclerViewItem
import retrofit2.http.GET

interface Api {

    @GET("movies")
    suspend fun getMovies(): retrofit2.Response<List<HomeRecyclerViewItem.Movie>>

    @GET("directors")
    suspend fun getDirectors(): retrofit2.Response<List<HomeRecyclerViewItem.Director>>

}