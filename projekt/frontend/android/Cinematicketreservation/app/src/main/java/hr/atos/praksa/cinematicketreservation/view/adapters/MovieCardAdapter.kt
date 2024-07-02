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
import hr.atos.praksa.cinematicketreservation.viewmodel.MovieViewModel

class MovieCardAdapter (private var list: List<MovieDataModel>) : RecyclerView.Adapter<MovieCardAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_design, parent, false)
        return ViewHolder(view)
    }

    fun setItems(movies: List<MovieDataModel>){
        list = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieCardAdapter.ViewHolder, position: Int) {
        val movie = list[position]

        Glide.with(holder.itemView.context)
            .load(movie.imgUrl)
            .into(holder.image)

        holder.name.text = movie.name
        holder.description.text = movie.description
        holder.genre.text = movie.genre
        holder.duration.text = "${movie.duration} minutes"
        holder.actors.text = movie.actors
        holder.director.text = movie.director
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView){
        val image: ImageView =  itemView.findViewById(R.id.movie_image)
        val name: TextView = itemView.findViewById(R.id.movie_title)
        val description: TextView = itemView.findViewById(R.id.movie_description)
        val genre: TextView = itemView.findViewById(R.id.genre)
        val duration: TextView = itemView.findViewById(R.id.duration)
        val actors: TextView = itemView.findViewById(R.id.actors)
        val director: TextView = itemView.findViewById(R.id.director)
    }
}