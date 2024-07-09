package hr.atos.praksa.cinematicketreservation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel

class ScreeningCardAdapter(private var screeningsList: List<ScreeningDataModel>) :
    RecyclerView.Adapter<ScreeningCardAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.screening_button_design, parent, false)
        return ViewHolder(view, onItemClickListener, screeningsList)
    }

    fun setItems(screenings: List<ScreeningDataModel>) {
        screeningsList = screenings
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ScreeningCardAdapter.ViewHolder, position: Int) {
        val screening = screeningsList[position]

        holder.btnScreening.text = screening.startTime
    }

    override fun getItemCount(): Int {
        return screeningsList.size
    }


    class ViewHolder(
        itemView: View,
        private val onItemClickListener: OnItemClickListener?,
        private val screeningsList: List<ScreeningDataModel>
    ) : RecyclerView.ViewHolder(itemView) {

        val btnScreening: TextView = this.itemView.findViewById(R.id.btn_screening)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(screeningsList[position])
                }
            }
        }
    }


    interface OnItemClickListener {
        fun onItemClick(screening: ScreeningDataModel) }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener}
}