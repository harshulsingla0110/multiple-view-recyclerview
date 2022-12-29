package com.harshul.recyclermultiviewapp.data.repos

import com.harshul.recyclermultiviewapp.data.api.Client

object MoviesRepository {

    suspend fun getMovies() = Client.api.getMovies()

    suspend fun getDirectors() = Client.api.getDirectors()

}