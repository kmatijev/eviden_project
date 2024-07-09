package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.SeatAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import kotlinx.coroutines.launch

class SeatsFragment: Fragment(R.layout.fragment_seats), SeatAdapter.SeatSelectionListener{
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var seatGridView: GridView
    private lateinit var seatsList: List<ScreeningSeatDataModel>
    private lateinit var reserveButton: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seatGridView = view.findViewById(R.id.seats_grid)
        reserveButton = view.findViewById(R.id.reserve_button)
        reserveButton.isEnabled = false
        reserveButton.isClickable = false
        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.seats.observe(viewLifecycleOwner) { seats ->
            val seatAdapter = SeatAdapter(requireContext(), seats, this)
            seatGridView.adapter = seatAdapter
            Log.d("HomeFragment.kt", "onViewCreated: $seats")
        }

    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchSeats()
    }

    override fun onSeatSelected(position: Int) {
        reserveButton.isEnabled = true
        reserveButton.isClickable = true
    }

    override fun onSeatDeselected() {
        reserveButton.isEnabled = false
        reserveButton.isClickable = false
    }
}