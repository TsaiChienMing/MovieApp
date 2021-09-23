package tw.tcnr02.movieapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import tw.tcnr02.movieapp.model.data.TvShowItem
import tw.tcnr02.movieapp.model.data.TvShowResponse
import tw.tcnr02.movieapp.model.repository.TvShowRepository
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val repository: TvShowRepository) : ViewModel() {

    private val _responseTvShow = MutableLiveData<List<TvShowItem>>()
    val responseTvShow get() = _responseTvShow

    init {
        getAllTvShow()
    }

    private fun getAllTvShow() = viewModelScope.launch {
        repository.getShows().let { response ->
            if (response.isSuccessful) {
                _responseTvShow.postValue(response.body())
            } else {
                Log.e("tag", "getAllTvShow error: + ${response.code()}")
            }
        }
    }
}