package hr.atos.praksa.cinematicketreservation.model.apis

import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MovieAPIService {
    @GET("http://192.168.1.44:3000/movies/")
    suspend fun getMovies(): Response<List<MovieDataModel>>
}