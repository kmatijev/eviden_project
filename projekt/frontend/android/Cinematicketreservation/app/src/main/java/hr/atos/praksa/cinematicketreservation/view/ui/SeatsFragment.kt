package hr.atos.praksa.cinematicketreservation.view.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.atos.praksa.cinematicketreservation.R
import java.lang.reflect.Type
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel
import hr.atos.praksa.cinematicketreservation.model.models.TicketDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.SeatAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.filterSeats
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.getTickets
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.saveTicket
import kotlinx.coroutines.launch


class SeatsFragment: Fragment(R.layout.fragment_seats), SeatAdapter.SeatSelectionListener{
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var seatGridView: GridView
    private lateinit var seatsList: List<ScreeningSeatDataModel>
    private lateinit var reserveButton: Button
    private lateinit var selectedSeat: ScreeningSeatDataModel
    private lateinit var ticket: TicketDataModel
    private var seatNumber: Int = 0
    private val args: SeatsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seatGridView = view.findViewById(R.id.seats_grid)
        reserveButton = view.findViewById(R.id.reserve_button)
        reserveButton.isEnabled = false
        reserveButton.isClickable = false

        val sharedPreferences = activity?.getSharedPreferences("tickets", MODE_PRIVATE)

        var ticketList: ArrayList<TicketDataModel> = getTickets(sharedPreferences!!) as ArrayList<TicketDataModel>

        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.seats.observe(viewLifecycleOwner) { seats ->
            seatsList = filterSeats(args.screening, seats)
            val seatAdapter = SeatAdapter(requireContext(), seatsList, this)
            seatGridView.adapter = seatAdapter
        }

        reserveButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("tickets", MODE_PRIVATE)

            ticket = TicketDataModel(
                seatId = selectedSeat.id ?: return@setOnClickListener,
                movieId = args.screening.movieId,
                seatNumber = seatNumber.toString(),
                screeningId = args.screening.id
            )

            ticketList.add(ticket)

            if (sharedPreferences != null) {
                saveTicket(sharedPreferences, ticketList)
            }

            val toast = Toast.makeText(context, "Seat reserved successfully", Toast.LENGTH_SHORT)
            toast.show()
            val action = SeatsFragmentDirections.actionSeatsFragmentToHomeFragment()

            view.findNavController().navigate(action)
        }
    }


    private suspend fun makeFetchRequest(){
        movieViewModel.fetchSeats()
    }

    override fun onSeatSelected(position: Int) {
        reserveButton.isEnabled = true
        reserveButton.isClickable = true
        seatNumber = position+1
        selectedSeat = movieViewModel.seats.value?.get(position)!!
    }

    override fun onSeatDeselected() {
        reserveButton.isEnabled = false
        reserveButton.isClickable = false
    }
}