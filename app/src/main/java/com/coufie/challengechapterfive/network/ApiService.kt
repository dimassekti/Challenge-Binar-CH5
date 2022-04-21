package com.coufie.challengechapterfive.network

import com.coufie.challengechapterfive.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("apifilm.php")
    fun getAllFilm() : Call<List<GetFilmDataItem>>

    @GET("apiuser.php")
    fun getUser(
        @Path ("id") id : String
    ) : Call<List<GetUserDataItem>>

    @POST("register.php/")
    @FormUrlEncoded
    fun registerUser(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("username") username : String
    ) : Call<ResponseUserRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<ResponseUserLogin>

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id : String,
        @Field("address") address : String,
        @Field("dateofbirth") dateofbirth : String,
        @Field("complete_name") complete_name : String,
        @Field("username") username : String

    ) : Call<ResponseUserUpdate>


}
