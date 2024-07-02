package hr.atos.praksa.cinematicketreservation.model.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDataModel(
    @SerializedName("id"          ) var id          : String? = null,
    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("genre"       ) var genre       : String? = null,
    @SerializedName("duration"    ) var duration    : Int?    = null,
    @SerializedName("actors"      ) var actors      : String? = null,
    @SerializedName("director"    ) var director    : String? = null,
    @SerializedName("img_url"     ) var imgUrl      : String? = null
):Serializable