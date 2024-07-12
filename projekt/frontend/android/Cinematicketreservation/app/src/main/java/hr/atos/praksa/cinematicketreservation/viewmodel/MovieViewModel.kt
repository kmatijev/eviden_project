package hr.atos.praksa.cinematicketreservation.viewmodel

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningSeatDataModel
import hr.atos.praksa.cinematicketreservation.model.models.TicketDataModel
import hr.atos.praksa.cinematicketreservation.model.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.TickerMode
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import java.util.ArrayList

class MovieViewModel(): ViewModel() {
    var movies = MutableLiveData<List<MovieDataModel>>()
    var screenings = MutableLiveData<List<ScreeningDataModel>>()
    var seats = MutableLiveData<List<ScreeningSeatDataModel>>()
    var tickets = MutableLiveData<List<TicketDataModel>>()

    suspend fun fetchMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = MovieRepository.getMovies()
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    Log.d("MovieViewModel.kt", "onCreate: ${response.body()}")
                    if (!response.body().isNullOrEmpty()) {
                        movies.postValue(response.body())
                    }
                }
            }
        }
    }

    suspend fun fetchScreenings(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = MovieRepository.getScreenings()
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    Log.d("ScreeningViewModel.kt", "onCreate: ${response.body()}")
                    if (!response.body().isNullOrEmpty()) {
                        screenings.postValue(response.body())
                    }
                }
            }
        }
    }

    suspend fun fetchSeats(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = MovieRepository.getSeats()
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    Log.d("MovieViewModel.kt", "onCreate: ${response.body()}")
                    if (!response.body().isNullOrEmpty()) {
                        seats.postValue(response.body())
                    }
                }
            }
        }
    }

    companion object{
        fun filterMovies(movieList: List<MovieDataModel>, screeningList: List<ScreeningDataModel>): List<MovieDataModel> {
            val filteredMovieList = movieList.filter { movie ->
                screeningList.any { it.movieId == movie.id && it.isOngoing == true }
            }
            return filteredMovieList
        }

        fun filterScreenings(screeningsList: List<ScreeningDataModel>, movie: MovieDataModel): List<ScreeningDataModel> {
            val filteredScreeningsList = screeningsList.filter { it.movieId == movie.id }
            return filteredScreeningsList
        }

        fun filterSeats(screening: ScreeningDataModel, seats: List<ScreeningSeatDataModel>): List<ScreeningSeatDataModel>{
            val filteredSeatsList = seats.filter { seat ->
                screening.id == seat.screeningId
            }
            return filteredSeatsList
        }

        fun saveTicket(sharedPreferences: SharedPreferences, ticketList: ArrayList<TicketDataModel>){
            val gson = Gson()

            val ticketJson: String = gson.toJson(ticketList)

            with(sharedPreferences.edit()){
                putString("tickets", ticketJson)
                apply()
            }
        }

        fun getTickets(sharedPreferences: SharedPreferences): List<TicketDataModel> {
            val gson = Gson()
            val json = sharedPreferences.getString("tickets", null)
            val type: Type = object : TypeToken<ArrayList<TicketDataModel>>() {}.type
            return if (json != null) {
                gson.fromJson(json, type)
            } else {
                ArrayList()
            }
        }
    }
}