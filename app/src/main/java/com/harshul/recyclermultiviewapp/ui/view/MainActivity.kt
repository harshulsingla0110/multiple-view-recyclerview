package com.harshul.recyclermultiviewapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.harshul.recyclermultiviewapp.data.models.HomeRecyclerViewItem
import com.harshul.recyclermultiviewapp.databinding.ActivityMainBinding
import com.harshul.recyclermultiviewapp.ui.adapter.HomeRecyclerViewAdapter
import com.harshul.recyclermultiviewapp.ui.viewmodel.MoviesViewModel


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val vm by lazy { ViewModelProvider(this)[MoviesViewModel::class.java] }
    private val homeRecyclerViewAdapter = HomeRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeRecyclerViewAdapter
        }

        homeRecyclerViewAdapter.itemClickListener = { view, item, position ->
            val message = when (item) {
                is HomeRecyclerViewItem.Director -> "Director ${item.name} Clicked"
                is HomeRecyclerViewItem.Movie -> "Movie ${item.title} Clicked"
                is HomeRecyclerViewItem.Title -> "View All Clicked"
            }
            Snackbar.make(
                binding.recyclerView,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        }

        vm.homeListItemsLiveData.observe(this) { result ->
            result.forEach {
                Log.d("TAG", "onCreate: $it")
                homeRecyclerViewAdapter.items = result
            }
        }

    }

}