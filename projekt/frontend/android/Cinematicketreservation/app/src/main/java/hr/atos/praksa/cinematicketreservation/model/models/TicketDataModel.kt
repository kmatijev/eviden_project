package hr.atos.praksa.cinematicketreservation.model.models

import com.google.gson.annotations.SerializedName

data class TicketDataModel(
    @SerializedName("seat_id"     ) var seatId:      String? =  null,
    @SerializedName("seat_number" ) var seatNumber:  String? =  null,
    @SerializedName("screening_id") var screeningId: String? =  null,
    @SerializedName("movie_id"    ) var movieId:     String? = null,
)
