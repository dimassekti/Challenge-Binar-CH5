package com.coufie.challengechapterfive.detail_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coufie.challengechapterfive.HomeActivity
import com.coufie.challengechapterfive.LoginActivity
import com.coufie.challengechapterfive.R
import com.coufie.challengechapterfive.model.ResponseUserUpdate
import com.coufie.challengechapterfive.network.ApiClient
import kotlinx.android.synthetic.main.activity_detail_user.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field

class DetailUser : AppCompatActivity() {

    lateinit var prefsLogin : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        prefsLogin = this.getSharedPreferences("SF", Context.MODE_PRIVATE)

        btn_update_profile.setOnClickListener {
            val address = et_update_address.text.toString()
            val dob = et_update_dob.text.toString()
            val username = et_update_username.text.toString()
            val fullname = et_update_name.text.toString()
            val id = "133"

            postUserUpdate(id.toString(), address, dob, fullname, username)
        }

        btn_logout.setOnClickListener {
            prefsLogin.edit().clear().apply()
            startActivity(Intent(this@DetailUser, LoginActivity::class.java))
        }
    }

    fun postUserUpdate(id : String, address : String, dateofbirth : String,complete_name : String, username : String){
        ApiClient.instance.updateUser(id, address, dateofbirth, complete_name, username)
            .enqueue(object : retrofit2.Callback<ResponseUserUpdate>{
                override fun onResponse(
                    call: Call<ResponseUserUpdate>,
                    response: Response<ResponseUserUpdate>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@DetailUser, "Update berhasil", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@DetailUser, HomeActivity::class.java))
                    }else{
                        Toast.makeText(this@DetailUser, "Update berhasil", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                    Toast.makeText(this@DetailUser, "Update gagal", Toast.LENGTH_LONG).show()
                }

            })

    }    }
