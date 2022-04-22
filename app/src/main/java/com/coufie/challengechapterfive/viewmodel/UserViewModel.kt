package com.coufie.challengechapterfive.viewmodel

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.challengechapterfive.HomeActivity
import com.coufie.challengechapterfive.model.GetUserDataItem
import com.coufie.challengechapterfive.model.ResponseUserLogin
import com.coufie.challengechapterfive.model.ResponseUserUpdate
import com.coufie.challengechapterfive.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class UserViewModel : ViewModel(){

    lateinit var userLiveData: MutableLiveData<List<GetUserDataItem>>
    lateinit var userUpdateLiveData: MutableLiveData<ResponseUserUpdate>

    init {
        userLiveData = MutableLiveData()
    }

    fun getUserLiveDataObserver() : MutableLiveData<List<GetUserDataItem>>{
        return userLiveData
    }

    fun makeApiUser(){
        ApiClient.instance.getAllUser()
            .enqueue(object  : retrofit2.Callback<List<GetUserDataItem>>{
                override fun onResponse(
                    call: Call<List<GetUserDataItem>>,
                    response: Response<List<GetUserDataItem>>
                ) {
                    if(response.isSuccessful){
                        userLiveData.postValue(response.body())
                    }else{
                        userLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetUserDataItem>>, t: Throwable) {
                    userLiveData.postValue(null)
                }


            })
    }

    fun postUserUpdate(id : String, address : String, dateofbirth : String,complete_name : String, username : String){
        ApiClient.instance.updateUser(id, address, dateofbirth, complete_name, username)
            .enqueue(object : retrofit2.Callback<ResponseUserUpdate>{
                override fun onResponse(
                    call: Call<ResponseUserUpdate>,
                    response: Response<ResponseUserUpdate>
                ) {
                    if(response.isSuccessful){
                        userUpdateLiveData.postValue(response.body())
                    }else{
                        userUpdateLiveData.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                    userUpdateLiveData.postValue(null)
                }

            })

    }

}