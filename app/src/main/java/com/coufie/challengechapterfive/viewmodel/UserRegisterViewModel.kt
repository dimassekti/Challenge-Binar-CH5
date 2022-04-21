package com.coufie.challengechapterfive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.challengechapterfive.model.ResponseUserRegister
import com.coufie.challengechapterfive.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class UserRegisterViewModel : ViewModel() {

    lateinit var userLiveDataRegis : MutableLiveData<List<ResponseUserRegister>>

    init {
        userLiveDataRegis = MutableLiveData()
    }

    fun getUserRLD( ) : MutableLiveData<List<ResponseUserRegister>>{
        return userLiveDataRegis
    }

    fun callUsersRegister(email : String, password : String, username : String){
        ApiClient.instance.registerUser(email, password, username)
            .enqueue(object : retrofit2.Callback<ResponseUserRegister>{
                override fun onResponse(
                    call: Call<ResponseUserRegister>,
                    response: Response<ResponseUserRegister>
                ) {
                    if(response.isSuccessful){

                    }
                }

                override fun onFailure(call: Call<ResponseUserRegister>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}