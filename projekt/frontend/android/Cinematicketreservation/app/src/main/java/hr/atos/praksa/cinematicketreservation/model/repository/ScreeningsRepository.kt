package hr.atos.praksa.cinematicketreservation.model.repository

import hr.atos.praksa.cinematicketreservation.model.apis.ScreeningsAPIService
import hr.atos.praksa.cinematicketreservation.model.models.ScreeningDataModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScreeningsRepository private constructor() {
    companion object{
        private val screeningsApiService: ScreeningsAPIService
        @Volatile private var instance: ScreeningsRepository? = null

        fun getInstance(){
            instance ?: synchronized(this){
                instance ?: ScreeningsRepository().also { instance = it }
            }
        }
        init{
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.44:3000/screenings/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            screeningsApiService = retrofit.create(ScreeningsAPIService::class.java)
        }

        suspend fun getScreenings(): Response<List<ScreeningDataModel>> {
            return screeningsApiService.getScreenings()
        }
    }
}