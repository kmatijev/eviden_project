package hr.atos.praksa.cinematicketreservation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import hr.atos.praksa.cinematicketreservation.model.repository.ScreeningsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreeningViewModel(): ViewModel() {
    var screenings = MutableLiveData<List<ScreeningDataModel>>()

    suspend fun fetchScreenings(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = ScreeningsRepository.getScreenings()
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
}