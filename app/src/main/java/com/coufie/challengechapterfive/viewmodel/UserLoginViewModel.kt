package com.coufie.challengechapterfive.viewmodel

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coufie.challengechapterfive.HomeActivity
import com.coufie.challengechapterfive.model.GetUserDataItem
import com.coufie.challengechapterfive.model.ResponseUserLogin
import com.coufie.challengechapterfive.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class UserLoginViewModel : ViewModel(){

    lateinit var userLiveData: MutableLiveData<List<ResponseUserLogin>>

    init {
        userLiveData = MutableLiveData()
    }

    fun getUserLLD() : MutableLiveData<List<ResponseUserLogin>>{
        return userLiveData
    }

    fun callUsersLogin(email : String, password : String){
        ApiClient.instance.loginUser(email, password)
            .enqueue(object  : retrofit2.Callback<ResponseUserLogin>{
                override fun onResponse(
                    call: Call<ResponseUserLogin>,
                    response: Response<ResponseUserLogin>
                ) {
                    if(response.isSuccessful){

                    }
                }

                override fun onFailure(call: Call<ResponseUserLogin>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}