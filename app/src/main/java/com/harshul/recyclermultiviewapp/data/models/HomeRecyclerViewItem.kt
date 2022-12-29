package com.harshul.recyclermultiviewapp.data.models

sealed class HomeRecyclerViewItem {

    class Title(
        val id: Int,
        val title: String
    ) : HomeRecyclerViewItem()

    class Movie(
        val id: Int,
        val title: String,
        val thumbnail: String,
        val release_date: String,
        val duration: String,
        val rating: String,
        val category: String,
        val reviews: String,
        val popularity: String
    ) : HomeRecyclerViewItem()

    class Director(
        val id: Int,
        val name: String,
        val avatar: String,
        val movie_count: Int,
        val known_for: String
    ) : HomeRecyclerViewItem()

}