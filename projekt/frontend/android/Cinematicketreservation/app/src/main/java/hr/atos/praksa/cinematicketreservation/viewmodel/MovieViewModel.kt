package hr.atos.praksa.cinematicketreservation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.atos.praksa.cinematicketreservation.model.models.MovieDataModel
import hr.atos.praksa.cinematicketreservation.model.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {
    var movies = MutableLiveData<List<MovieDataModel>>()
    val error = MutableLiveData<String>()

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
}