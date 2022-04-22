package com.coufie.challengechapterfive

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coufie.challengechapterfive.model.GetUserDataItem
import com.coufie.challengechapterfive.model.ResponseUserLogin
import com.coufie.challengechapterfive.network.ApiClient
import com.coufie.challengechapterfive.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var prefsLogin : SharedPreferences
    lateinit var viewModel : UserViewModel
    lateinit var dataUser : List<GetUserDataItem>
    lateinit var email: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefsLogin = this.getSharedPreferences("SF", Context.MODE_PRIVATE)
        var username = prefsLogin.getString("USERNAME", "")
        prefsLogin.edit().putString("USERNAME", username).apply()

        if(this.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("EMAIL") && this.getSharedPreferences("SF", Context.MODE_PRIVATE).contains("PASSWORD")){
            startActivity(Intent(this, HomeActivity::class.java))
        }
        getUserData()
        login()
        register()


    }

    fun login(){
        btn_login.setOnClickListener {

            if(et_input_email.text.isEmpty() || et_input_password.text.isEmpty()){
                Toast.makeText(this, "Email atau Password Tidak boleh kosong", Toast.LENGTH_LONG).show()
            }else{
                email = et_input_email.text.toString()
                password = et_input_password.text.toString()

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
                        for(i in dataUser.indices){
                            if(email == dataUser[i].email && password == dataUser[i].password){
                                prefsLogin.edit().putString("ID", dataUser[i].id).apply()
                                prefsLogin.edit().putString("EMAIL", dataUser[i].email).apply()
                                prefsLogin.edit().putString("PASSWORD", dataUser[i].password).apply()
                                prefsLogin.edit().putString("USERNAME", dataUser[i].username).apply()
                                prefsLogin.edit().putString("FULLNAME", dataUser[i].completeName).apply()
                                prefsLogin.edit().putString("ADDRESS", dataUser[i].address).apply()
                                prefsLogin.edit().putString("DOB", dataUser[i].dateofbirth).apply()

                                Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_LONG).show()
                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                            }
                        }

                    }else{
                        Toast.makeText(this@LoginActivity, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseUserLogin>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Data tidak ditemukan", Toast.LENGTH_LONG).show()
                }

            })
    }

    fun getUserData(){
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getUserLiveDataObserver().observe(this, Observer {
            dataUser = it
        })
        viewModel.makeApiUser()
    }

    fun register(){
        tv_goto_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        getUserData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}