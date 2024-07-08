package hr.atos.praksa.cinematicketreservation.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScreeningDataModel(
    @SerializedName("id"        ) var id          : String? =  null,
    @SerializedName("start_time") var startTime   : String? =  null,
    @SerializedName("date"      ) var date        : String? =  null,
    @SerializedName("movie_id"  ) var movieId     : String? =  null,
    @SerializedName("ongoing"   ) var isOngoing   : Boolean? = null,
):Serializable
