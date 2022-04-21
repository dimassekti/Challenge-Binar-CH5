package com.coufie.challengechapterfive

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coufie.challengechapterfive.model.ResponseUserLogin
import com.coufie.challengechapterfive.network.ApiClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var prefsLogin : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefsLogin = this.getSharedPreferences("SF", Context.MODE_PRIVATE)

        if(this.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("EMAIL") && this.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("PASSWORD")){
            startActivity(Intent(this, HomeActivity::class.java))
        }

        login()
        register()
    }

    fun login(){
        btn_login.setOnClickListener {

            if(et_input_email.text.isEmpty() || et_input_password.text.isEmpty()){
                Toast.makeText(this, "Email atau Password Tidak boleh kosong", Toast.LENGTH_LONG).show()
            }else{
                var email = et_input_email.text.toString()
                var password = et_input_password.text.toString()

                postUserLogin(email, password)
            }

        }
    }

    fun postUserLogin(email:String, password:String){
        ApiClient.instance.loginUser(email, password)
            .enqueue(object : retrofit2.Callback<ResponseUserLogin>{
                override fun onResponse(
                    call: Call<ResponseUserLogin>,
                    response: Response<ResponseUserLogin>
                ) {
                    if (response.isSuccessful){
                        prefsLogin.edit().putString("EMAIL", email).apply()
                        prefsLogin.edit().putString("PASSWORD", password).apply()
                        Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    }else{
                        Toast.makeText(this@LoginActivity, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUserLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                }

            })
    }

    fun register(){
        tv_goto_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}