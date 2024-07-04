package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.databinding.FragmentReservationBinding

class ReservationFragment : Fragment(R.layout.fragment_reservation) {

    private lateinit var binding: FragmentReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReservationBinding.inflate(inflater, container, false)

        binding.btShowmore.setOnClickListener {
            if (binding.body.maxLines == 3) {
                binding.body.maxLines = Int.MAX_VALUE
                binding.btShowmore.text = "Show less..."
                binding.btShowmore.setIconResource(R.drawable.arrow_up)

            } else {
                binding.body.maxLines = 3
                binding.btShowmore.text = "Show more..."
                binding.btShowmore.setIconResource(R.drawable.arrow_down)
            }
        }

        binding.movieImage.setImageResource()

        return binding.root
    }
}