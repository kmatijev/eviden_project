package hr.atos.praksa.cinematicketreservation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.model.models.TicketDataModel

class TicketCardAdapter(private var ticketList: List<TicketDataModel>, private var moviesList: List<MovieDataModel>, private var screeningsList: List<ScreeningDataModel>) : RecyclerView.Adapter<TicketCardAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketCardAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_card_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ticketList.size
    }

    fun setItems(tickets: List<TicketDataModel>, movies: List<MovieDataModel>, screenings: List<ScreeningDataModel>){
        ticketList = tickets
        moviesList = movies
        screeningsList = screenings
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TicketCardAdapter.ViewHolder, position: Int) {
        val ticket = ticketList[position]
        val movie = moviesList.find { it.id == ticket.movieId }
        val screening = screeningsList.find { it.id == ticket.screeningId}

        if(movie != null){
            Glide.with(holder.itemView.context)
                .load(movie.imgUrl)
                .into(holder.image)
            holder.name.text = movie.name
        }

        holder.seatNumber.text = ticket.seatNumber

        if (screening != null){
            holder.startTime.text = screening.startTime
        }
    }

    override fun onBindViewHolder(holder: TicketCardAdapter.ViewHolder, position: Int, payloads: MutableList<Any>) {

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = this.itemView.findViewById(R.id.ticket_movie_image)
        val name: TextView = this.itemView.findViewById(R.id.ticket_movie_title)
        val seatNumber: TextView = this.itemView.findViewById(R.id.seat_number)
        val startTime: TextView = this.itemView.findViewById(R.id.screening_time)
    }
}