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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.view.adapters.ScreeningCardAdapter
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel.Companion.filterScreenings
import kotlinx.coroutines.launch

class ReservationFragment : Fragment(R.layout.fragment_reservation) {

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

        nameTextView = view.findViewById(R.id.name)
        genreTextView = view.findViewById(R.id.genre)
        durationTextView = view.findViewById(R.id.duration)
        castTextView = view.findViewById(R.id.cast)
        directorTextView = view.findViewById(R.id.director)
        descriptionTextView = view.findViewById(R.id.description)
        movieImageView = view.findViewById(R.id.movieImage)
        showMoreButton = view.findViewById(R.id.bt_show_more)
        screeningsRecyclerView = view.findViewById(R.id.screenings_recycler_view)

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

        showMoreButton.setOnClickListener {
            if (descriptionTextView.maxLines == 2) {
                descriptionTextView.maxLines = Int.MAX_VALUE
                descriptionTextView.ellipsize = null // Remove ellipsis
                showMoreButton.text = "Show less..."
                showMoreButton.setIconResource(R.drawable.arrow_up)
            } else {
                descriptionTextView.maxLines = 2
                descriptionTextView.ellipsize = TextUtils.TruncateAt.END // Add ellipsis
                showMoreButton.text = "Show more..."
                showMoreButton.setIconResource(R.drawable.arrow_down)
            }
        }

        // Setup RecyclerView for screenings
        screeningsAdapter = ScreeningCardAdapter(emptyList())
        screeningsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = screeningsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            makeFetchRequest()
        }

        movieViewModel.screenings.observe(viewLifecycleOwner) { screenings ->
            screeningsList = screenings
            val filterScreenings = filterScreenings(screeningsList, args.movie)
            screeningsAdapter.setItems(filterScreenings)
            Log.i("ReservationFragment.kt", "onViewCreated: ${screenings}")
        }

        return view
    }

    private suspend fun makeFetchRequest(){
        movieViewModel.fetchScreenings()
    }
}