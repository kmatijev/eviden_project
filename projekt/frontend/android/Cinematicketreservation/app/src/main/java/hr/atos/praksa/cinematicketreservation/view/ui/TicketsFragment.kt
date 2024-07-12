package hr.atos.praksa.cinematicketreservation.view.ui

import android.content.Context
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
import hr.atos.praksa.cinematicketreservation.model.models.TicketDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.TicketCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.getTickets
import kotlinx.coroutines.launch

class TicketsFragment : Fragment(R.layout.fragment_ticket) {

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var tickets: List<TicketDataModel>
    private lateinit var ticketCardAdapter: TicketCardAdapter
    private lateinit var moviesList: List<MovieDataModel>
    private lateinit var screeningsList: List<ScreeningDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = activity?.getSharedPreferences("tickets", Context.MODE_PRIVATE)
        if (sharedPreferences != null){
            tickets = getTickets(sharedPreferences)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.ticket_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ticketCardAdapter = TicketCardAdapter(emptyList(), emptyList(), emptyList())
        Log.i("TicketsFragment.kt", "${tickets}")
        recyclerView.adapter = ticketCardAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.screenings.observe(viewLifecycleOwner) { screenings ->
            screeningsList = screenings
            if (::moviesList.isInitialized){
                ticketCardAdapter.setItems(tickets, moviesList, screeningsList)
            }
        }

        movieViewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesList = movies
            if (::screeningsList.isInitialized){
                ticketCardAdapter.setItems(tickets, moviesList, screeningsList)
            }
        }
    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchMovies()
        movieViewModel.fetchScreenings()
    }

}
