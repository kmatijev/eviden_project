package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.view.adapters.MovieCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.ScreeningViewModel
import kotlinx.coroutines.launch

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val movieViewModel: MovieViewModel by viewModels()
    private val screeningViewModel: ScreeningViewModel by viewModels()
    private lateinit var movieCardAdapter: MovieCardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieCardAdapter = MovieCardAdapter(emptyList(), emptyList())
        recyclerView.adapter = movieCardAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }
        screeningViewModel.screenings.observe(viewLifecycleOwner) { screenings ->
            movieCardAdapter.setScreenings(screenings)
            Log.d("HomeFragment.kt", "onViewCreated: ${screenings}")
        }
        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieCardAdapter.setMovies(movies)
            Log.d("HomeFragment.kt", "onViewCreated: ${movies}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchMovies()
        screeningViewModel.fetchScreenings()
    }
}