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
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.MovieCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.filterMovies
import kotlinx.coroutines.launch

class HomeFragment: Fragment(R.layout.fragment_home) {
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieCardAdapter: MovieCardAdapter
    private lateinit var filteredList: List<MovieDataModel>
    private lateinit var moviesList: List<MovieDataModel>
    private lateinit var screeningsList: List<ScreeningDataModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieCardAdapter = MovieCardAdapter(emptyList())
        recyclerView.adapter = movieCardAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.screenings.observe(viewLifecycleOwner) { screenings ->
            screeningsList = screenings
            if (::moviesList.isInitialized){
                filteredList = filterMovies(moviesList, screeningsList)
                movieCardAdapter.setItems(filteredList)
            }
            Log.d("HomeFragment.kt", "onViewCreated: ${screenings}")
        }

        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesList = movies
            if (::screeningsList.isInitialized){
                filteredList = filterMovies(moviesList, screeningsList)
                movieCardAdapter.setItems(filteredList)
            }
            Log.d("HomeFragment.kt", "onViewCreated: ${movies}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchMovies()
        movieViewModel.fetchScreenings()
    }
}