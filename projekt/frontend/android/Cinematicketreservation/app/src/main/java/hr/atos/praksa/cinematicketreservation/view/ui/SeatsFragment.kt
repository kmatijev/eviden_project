package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.SeatAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

class SeatsFragment: Fragment(R.layout.fragment_seats){
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var seatGridView: GridView
    private lateinit var seatsList: List<ScreeningSeatDataModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seatGridView = view.findViewById(R.id.seats_grid)
        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.seats.observe(viewLifecycleOwner) { seats ->
            val seatAdapter = SeatAdapter(requireContext(), seats)
            seatGridView.adapter = seatAdapter
            Log.d("HomeFragment.kt", "onViewCreated: $seats")
        }

    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchSeats()
    }
}