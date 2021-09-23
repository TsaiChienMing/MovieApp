package tw.tcnr02.movieapp.model.repository

import tw.tcnr02.movieapp.model.api.ApiService
import javax.inject.Inject

class TvShowRepository @Inject constructor(private val apiService:ApiService) {
    suspend fun getShows() = apiService.getTvShows()
}