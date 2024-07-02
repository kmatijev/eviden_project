package hr.atos.praksa.cinematicketreservation.model.apis

import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPIService {
    @GET("movies/")
    suspend fun getMovies(): Response<List<MovieDataModel>>
    @GET("screenings/")
    suspend fun getScreenings(): Response<List<ScreeningDataModel>>
}