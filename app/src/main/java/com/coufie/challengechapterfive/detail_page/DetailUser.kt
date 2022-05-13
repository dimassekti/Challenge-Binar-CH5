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
import com.coufie.challengechapterfive.model.GetFilmDataItem
import com.coufie.challengechapterfive.model.GetUserDataItem
import com.coufie.challengechapterfive.model.ResponseUserUpdate
import com.coufie.challengechapterfive.network.ApiClient
import kotlinx.android.synthetic.main.activity_detail_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class DetailUser : AppCompatActivity() {

    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val detailUser = intent.getParcelableExtra<GetUserDataItem>("DETAILUSER")

        prefs = this.getSharedPreferences("SF", Context.MODE_PRIVATE)

        var username = prefs.getString("USERNAME", "")
        val address = prefs.getString("ADDRESS", "")
        val dob = prefs.getString("DOB", "")
        val fullname = prefs.getString("FULLNAME", "")
        val idIN = prefs.getString("ID", "")

        if(address!!.length > 0 || address!!.length > 0){
            et_update_address.setText(address)
        }
        if(dob!!.length > 0 || dob!!.length > 0){
            et_update_dob.setText(dob)
        }
        if(fullname!!.length > 0 || fullname!!.length > 0){
            et_update_name.setText(fullname)
        }

        et_update_username.setText(username)


        btn_update_profile.setOnClickListener {

            if (et_update_address.text.isNotEmpty()
                && et_update_dob.text.isNotEmpty()
                && et_update_username.text.isNotEmpty()
                && et_update_name.text.isNotEmpty()){

                val address = et_update_address.text.toString()
                val dob = et_update_dob.text.toString()
                val username = et_update_username.text.toString()
                val fullname = et_update_name.text.toString()
                val id = idIN

                prefs.edit().putString("FULLNAME", fullname).apply()
                prefs.edit().putString("USERNAME", username).apply()
                prefs.edit().putString("ADDRESS", address).apply()
                prefs.edit().putString("DOB", dob).apply()

                Toast.makeText(this@DetailUser, "Update berhasil", Toast.LENGTH_LONG).show()
                postUserUpdate(id.toString(), address, dob, fullname, username)
            }else{
                Toast.makeText(this@DetailUser, "Data belum lengkap", Toast.LENGTH_LONG).show()
            }

        }

        btn_logout.setOnClickListener {
            prefs.edit().clear().apply()
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
                        startActivity(Intent(this@DetailUser, LoginActivity::class.java))
                    }else{
                        Toast.makeText(this@DetailUser, "Update berhasil", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                    finish()
                }

            })

        }
    }
