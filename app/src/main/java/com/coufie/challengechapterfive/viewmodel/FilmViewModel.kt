package com.coufie.challengechapterfive.viewmodel

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.challengechapterfive.adapter.FilmAdapter
import com.coufie.challengechapterfive.model.GetFilmDataItem
import com.coufie.challengechapterfive.network.ApiClient
import retrofit2.Call
import retrofit2.Response


class FilmViewModel : ViewModel() {

    lateinit var filmLiveData : MutableLiveData<List<GetFilmDataItem>>

    init {
        filmLiveData = MutableLiveData()
    }

    fun getFilmLLD() : MutableLiveData<List<GetFilmDataItem>>{
        return filmLiveData
    }

    fun callFilmApi(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : retrofit2.Callback<List<GetFilmDataItem>>{
                override fun onResponse(
                    call: Call<List<GetFilmDataItem>>,
                    response: Response<List<GetFilmDataItem>>
                ) {
                    if(response.isSuccessful){
                        filmLiveData.postValue(response.body())
                    }else{
                        filmLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetFilmDataItem>>, t: Throwable) {
                    filmLiveData.postValue(null)
                }


            })
    }


}