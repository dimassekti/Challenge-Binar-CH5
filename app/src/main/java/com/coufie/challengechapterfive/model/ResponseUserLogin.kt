package com.coufie.challengechapterfive.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUserLogin (

    @SerializedName("email")
    val description: String,
    @SerializedName("password")
    val director: String

) : Parcelable