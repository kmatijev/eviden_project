package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.ScreeningCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.filterScreenings
import kotlinx.coroutines.launch

class ReservationFragment : Fragment(R.layout.fragment_reservation), ScreeningCardAdapter.OnItemClickListener {

    private lateinit var nameTextView: TextView
    private lateinit var genreTextView: TextView
    private lateinit var durationTextView: TextView
    private lateinit var castTextView: TextView
    private lateinit var directorTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var movieImageView: ImageView
    private lateinit var showMoreButton: MaterialButton
    private lateinit var screeningsRecyclerView: RecyclerView
    private lateinit var screeningsAdapter: ScreeningCardAdapter
    private lateinit var screeningsList: List<ScreeningDataModel>
    private val movieViewModel: MovieViewModel by viewModels()

    private val args: ReservationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reservation, container, false)

        screeningsRecyclerView = view.findViewById(R.id.screenings_recycler_view)
        screeningsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        screeningsAdapter = ScreeningCardAdapter(emptyList())
        screeningsRecyclerView.adapter = screeningsAdapter
        screeningsAdapter.setOnItemClickListener(this)

        nameTextView = view.findViewById(R.id.name)
        genreTextView = view.findViewById(R.id.genre)
        durationTextView = view.findViewById(R.id.duration)
        castTextView = view.findViewById(R.id.cast)
        directorTextView = view.findViewById(R.id.director)
        descriptionTextView = view.findViewById(R.id.description)
        movieImageView = view.findViewById(R.id.movieImage)


        // Load image using Glide
        Glide.with(this)
            .load(args.movie.imgUrl)
            .into(movieImageView)

        nameTextView.text = args.movie.name
        genreTextView.text = args.movie.genre
        durationTextView.text = getString(R.string.movie_duration, args.movie.duration)
        castTextView.text = getString(R.string.movie_cast, args.movie.actors)
        directorTextView.text = getString(R.string.movie_director, args.movie.director)
        descriptionTextView.text = args.movie.description



        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.screenings.observe(viewLifecycleOwner) { screenings ->
            screeningsList = screenings
            val filterScreenings = filterScreenings(screeningsList, args.movie)
            screeningsAdapter.setItems(filterScreenings)

        }



        return view
    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchScreenings()
    }

    override fun onItemClick(screening: ScreeningDataModel) {
        Log.i("ReservationFragment.kt", "onItemClick: ${screening.id}")
        val action = ReservationFragmentDirections.actionReservationFragmentToSeatsFragment(screening)
        if(view != null){
            view?.findNavController()?.navigate(action)
        }

}
}
