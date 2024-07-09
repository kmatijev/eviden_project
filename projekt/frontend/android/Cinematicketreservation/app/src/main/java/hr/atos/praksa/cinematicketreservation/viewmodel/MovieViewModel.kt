package hr.atos.praksa.cinematicketreservation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.model.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {
    var movies = MutableLiveData<List<MovieDataModel>>()
    var screenings = MutableLiveData<List<ScreeningDataModel>>()

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
    }
}