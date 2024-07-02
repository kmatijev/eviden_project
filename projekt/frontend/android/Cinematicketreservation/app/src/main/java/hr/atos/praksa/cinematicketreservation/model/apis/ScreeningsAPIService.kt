package hr.atos.praksa.cinematicketreservation.model.apis

import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import retrofit2.Response
import retrofit2.http.GET

interface ScreeningsAPIService{
    @GET("http://192.168.1.44:3000/screenings/")
    suspend fun getScreenings(): Response<List<ScreeningDataModel>>
}