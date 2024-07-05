package hr.atos.praksa.cinematicketreservation.view.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import hr.atos.praksa.cinematicketreservation.R
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel

class SeatAdapter(context: Context, seats: List<ScreeningSeatDataModel>): ArrayAdapter<ScreeningSeatDataModel>(context, 0, seats){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false)
        }
        val seat = getItem(position)
        val seatImage = listItemView?.findViewById<ImageView>(R.id.seat_image)

        if (seat?.status == false) {
            seatImage?.setColorFilter(Color.RED)
            seatImage?.isClickable = false
        }

        return listItemView!!
    }
}
