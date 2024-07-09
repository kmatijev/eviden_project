package hr.atos.praksa.cinematicketreservation.view.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel

class SeatAdapter(context: Context, seats: List<ScreeningSeatDataModel>, private val listener: SeatSelectionListener) : ArrayAdapter<ScreeningSeatDataModel>(context, 0, seats) {

    private var selectedPosition: Int? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }
        val seat = getItem(position)
        val seatImage = listItemView?.findViewById<ImageView>(R.id.seat_image)
        val seatNumber = listItemView?.findViewById<TextView>(R.id.seat_number)

        seatNumber?.text = (position+1).toString()
        if (seat?.status == false) {
            seatImage?.setColorFilter(Color.parseColor("#ea4766"))
            seatImage?.isEnabled = false
            seatImage?.isClickable = false
        } else {
            seatImage?.setColorFilter(if (position == selectedPosition) Color.parseColor("#478be9") else Color.GRAY)
            seatImage?.isEnabled = true
            seatImage?.isClickable = true
            seatImage?.setOnClickListener {
                if(selectedPosition == position){
                    selectedPosition = null
                    seat?.status = true
                    listener.onSeatDeselected()
                }else{
                    if (selectedPosition != null) {
                        val previousSelectedSeat = getItem(selectedPosition!!)
                        previousSelectedSeat?.status = true
                        listener.onSeatDeselected()
                    }
                    listener.onSeatSelected(position)
                    selectedPosition = position
                    seat?.status = true
                }
                notifyDataSetChanged()
            }
        }
        return listItemView!!
    }

    interface SeatSelectionListener{
        fun onSeatSelected(position: Int)
        fun onSeatDeselected()
    }
}
