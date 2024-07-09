package hr.atos.praksa.cinematicketreservation.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SeatDataModel(
    @SerializedName("id") var id : String? = null,
):Serializable
