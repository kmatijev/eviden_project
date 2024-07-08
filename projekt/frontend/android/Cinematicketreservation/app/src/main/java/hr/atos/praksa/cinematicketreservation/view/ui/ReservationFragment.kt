package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import hr.atos.praksa.cinematicketreservation.R

class ReservationFragment : Fragment(R.layout.fragment_reservation) {

    private val args: ReservationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)

        val nameTextView: TextView = view.findViewById(R.id.name)
        val genreTextView: TextView = view.findViewById(R.id.genre)
        val durationTextView: TextView = view.findViewById(R.id.duration)
        val castTextView: TextView = view.findViewById(R.id.cast)
        val directorTextView: TextView = view.findViewById(R.id.director)
        val descriptionTextView: TextView = view.findViewById(R.id.description)
        val movieImageView: ImageView = view.findViewById(R.id.movieImage)
        val showMoreButton: MaterialButton = view.findViewById(R.id.bt_show_more)

        Glide.with(this)
            .load(args.movie.imgUrl)
            //.placeholder(R.drawable.placeholder) // Optional placeholder
            //.error(R.drawable.error) // Optional error drawable
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
                showMoreButton.text = "Show less..."
                descriptionTextView.ellipsize = null
                showMoreButton.setIconResource(R.drawable.arrow_up)
            } else {
                descriptionTextView.maxLines = 2
                showMoreButton.text = "Show more..."
                showMoreButton.setIconResource(R.drawable.arrow_down)
            }
        }

        return view
    }
}
