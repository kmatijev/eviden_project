package hr.atos.praksa.cinematicketreservation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel

class MovieCardAdapter (private var movieList: List<MovieDataModel>) : RecyclerView.Adapter<MovieCardAdapter.ViewHolder>(){
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_card_design, parent, false)
        return ViewHolder(view, onItemClickListener, movieList)
    }

    fun setItems(movies: List<MovieDataModel>){
        movieList = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieCardAdapter.ViewHolder, position: Int) {
        val movie = movieList[position]

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
        return movieList.size
    }

    class ViewHolder (itemView: View, private val onItemClickListener: OnItemClickListener?, private val movieList: List<MovieDataModel>): RecyclerView.ViewHolder(itemView){
        val image: ImageView =  this.itemView.findViewById(R.id.movie_image)
        val name: TextView = this.itemView.findViewById(R.id.movie_title)
        val description: TextView = this.itemView.findViewById(R.id.movie_description)
        val genre: TextView = this.itemView.findViewById(R.id.genre)
        val duration: TextView = this.itemView.findViewById(R.id.duration)
        val actors: TextView = this.itemView.findViewById(R.id.actors)
        val director: TextView = this.itemView.findViewById(R.id.director)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(movieList[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: MovieDataModel) }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener}

}