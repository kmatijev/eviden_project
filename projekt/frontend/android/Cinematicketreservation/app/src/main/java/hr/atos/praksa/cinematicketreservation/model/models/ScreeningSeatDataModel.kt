package hr.atos.praksa.cinematicketreservation.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScreeningSeatDataModel(
    @SerializedName("id"          ) var id          : String? =  null,
    @SerializedName("screening_id") var screeningId : String? =  null,
    @SerializedName("status"      ) var isOngoing   : Boolean? = null,
): Serializable
