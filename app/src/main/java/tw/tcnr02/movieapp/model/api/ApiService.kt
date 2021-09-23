package tw.tcnr02.movieapp.model.api

import retrofit2.Response
import retrofit2.http.GET
import tw.tcnr02.movieapp.common.Constant
import tw.tcnr02.movieapp.model.data.TvShowResponse

interface ApiService {

    @GET(Constant.END_POINT)
    suspend fun getTvShows():Response<TvShowResponse>
}