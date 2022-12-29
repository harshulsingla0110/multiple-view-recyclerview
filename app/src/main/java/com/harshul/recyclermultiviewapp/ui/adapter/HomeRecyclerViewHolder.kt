package com.harshul.recyclermultiviewapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.harshul.recyclermultiviewapp.R
import com.harshul.recyclermultiviewapp.data.models.HomeRecyclerViewItem
import com.harshul.recyclermultiviewapp.databinding.ItemDirectorBinding
import com.harshul.recyclermultiviewapp.databinding.ItemMovieBinding
import com.harshul.recyclermultiviewapp.databinding.ItemTitleBinding
import io.getstream.avatarview.coil.loadImage
import kotlin.math.abs

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    var itemClickListener: ((view: View, item: HomeRecyclerViewItem, position: Int) -> Unit)? = null

    class TitleViewHolder(private val binding: ItemTitleBinding) : HomeRecyclerViewHolder(binding) {
        fun bind(title: HomeRecyclerViewItem.Title) {
            binding.textViewTitle.text = title.title
            binding.textViewAll.setOnClickListener {
                itemClickListener?.invoke(it, title, adapterPosition)
            }
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : HomeRecyclerViewHolder(binding) {
        fun bind(movie: HomeRecyclerViewItem.Movie) {
            Glide.with(binding.imageViewMovie.context).load(movie.thumbnail)
                .into(binding.imageViewMovie)
            binding.textViewName.text = movie.title
            binding.textViewCategory.text = movie.category
            binding.textViewDuration.text = movie.duration
            binding.textViewRating.text = "⭐ ${movie.rating}"
            binding.textViewReview.text = movie.reviews
            if (movie.popularity.toFloat() < 0) {
                binding.imageViewPopularity.setImageResource(R.drawable.ic_decrease)
            }
            binding.textViewPopularity.text = abs(movie.popularity.toInt()).toString()
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, movie, adapterPosition)
            }
        }
    }

    class DirectorViewHolder(private val binding: ItemDirectorBinding) :
        HomeRecyclerViewHolder(binding) {
        fun bind(director: HomeRecyclerViewItem.Director) {
            binding.imageViewDirector.loadImage(director.avatar)
            binding.textViewName.text = director.name
            binding.textViewMovies.text = binding.textViewMovies.context.getString(
                R.string.total_movies,
                director.movie_count
            )
            binding.textViewKnownFor.text = "Known For: ${director.known_for}"
            binding.root.setOnClickListener {
                itemClickListener?.invoke(it, director, adapterPosition)
            }
        }
    }
}