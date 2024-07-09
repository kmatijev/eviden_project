package hr.atos.praksa.cinematicketreservation.view.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    //val args: ProfileFragmentArgs by navArgs()
    //private lateinit var movie: MovieDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.textView)

        //movie = args.movie

        //textView.text = "${movie.name} ${movie.description} ${movie.actors}"
    }
}
