package com.coufie.challengechapterfive.model

import com.google.gson.annotations.SerializedName

data class GetDetailUser(
    @SerializedName("address")
    val address: String,
    @SerializedName("complete_name")
    val completeName: String,
    @SerializedName("dateofbirth")
    val dateofbirth: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String
)
