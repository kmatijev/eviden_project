package hr.atos.praksa.cinematicketreservation.model.repository

import android.util.Log
import hr.atos.praksa.cinematicketreservation.model.apis.MovieAPIService
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository private constructor(){
    companion object{
        private val movieApiService: MovieAPIService
        @Volatile private var instance: MovieRepository? = null

        fun getInstance(){
            instance ?: synchronized(this){
                instance ?: MovieRepository().also { instance = it }
            }
        }
        init{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.44:3000/movies/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            movieApiService = retrofit.create(MovieAPIService::class.java)
        }

        suspend fun getMovies(): Response<List<MovieDataModel>> {
            return movieApiService.getMovies()
        }
    }
}