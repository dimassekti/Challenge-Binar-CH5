package com.coufie.challengechapterfive.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUserLogin (

    @SerializedName("user")
    val user: String,
    @SerializedName("password")
    val password: String

) : Parcelable