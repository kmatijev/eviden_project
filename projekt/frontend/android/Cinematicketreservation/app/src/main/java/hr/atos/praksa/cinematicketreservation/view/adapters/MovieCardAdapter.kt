package hr.atos.praksa.cinematicketreservation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel

class MovieCardAdapter (private var movieList: List<MovieDataModel>, private var screeningList: List<ScreeningDataModel>) : RecyclerView.Adapter<MovieCardAdapter.ViewHolder>(){
    private var filteredMovieList: List<MovieDataModel> = movieList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_design, parent, false)
        return ViewHolder(view)
    }

    fun setMovies(movies: List<MovieDataModel>){
        movieList = movies
        filterMovies()
        notifyDataSetChanged()
    }

    fun setScreenings(screenings: List<ScreeningDataModel>){
        screeningList = screenings
        filterMovies()
        notifyDataSetChanged()
    }

    private fun filterMovies() {
        filteredMovieList = movieList.filter { movie ->
            screeningList.any { it.movieId == movie.id && it.isOngoing == true }
        }
    }

    override fun onBindViewHolder(holder: MovieCardAdapter.ViewHolder, position: Int) {
        val movie = filteredMovieList[position]

        Glide.with(holder.itemView.context)
            .load(movie.imgUrl)
            .into(holder.image)

        holder.name.text = movie.name
        holder.description.text = "${movie.description?.take(50) ?: movie.description}..."
        holder.genre.text = movie.genre
        holder.duration.text = "${movie.duration} minutes"
        holder.actors.text = "${movie.actors?.take(20) ?: movie.actors}..."
        holder.director.text = movie.director
    }

    override fun getItemCount(): Int {
        return filteredMovieList.size
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView =  this.itemView.findViewById(R.id.movie_image)
        val name: TextView = this.itemView.findViewById(R.id.movie_title)
        val description: TextView = this.itemView.findViewById(R.id.movie_description)
        val genre: TextView = this.itemView.findViewById(R.id.genre)
        val duration: TextView = this.itemView.findViewById(R.id.duration)
        val actors: TextView = this.itemView.findViewById(R.id.actors)
        val director: TextView = this.itemView.findViewById(R.id.director)
    }
}