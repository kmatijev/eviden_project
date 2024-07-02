package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.view.adapters.MovieCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieCardAdapter: MovieCardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieCardAdapter = MovieCardAdapter(emptyList())
        recyclerView.adapter = movieCardAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieCardAdapter.setItems(movies)
            Log.d("HomeFragment.kt", "onViewCreated: ${movies}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun makeFetchRequest(){
        viewModel.fetchMovies()
    }
}