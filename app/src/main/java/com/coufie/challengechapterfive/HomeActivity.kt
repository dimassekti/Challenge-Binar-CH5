package com.coufie.challengechapterfive

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.coufie.challengechapterfive.adapter.FilmAdapter
import com.coufie.challengechapterfive.detail_page.DetailFilm
import com.coufie.challengechapterfive.detail_page.DetailUser
import com.coufie.challengechapterfive.viewmodel.FilmViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var adapterfilm : FilmAdapter
    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        prefs = this.getSharedPreferences("SF", Context.MODE_PRIVATE)

        var username = prefs.getString("EMAIL", "")

        welcome.setText("Welcome, $username")

        initRecycler()
        getDataFilm()

        iv_profile.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DetailUser::class.java))
        }

    }

    fun initRecycler(){

        adapterfilm = FilmAdapter(){
            val pindah = Intent(this, DetailFilm::class.java)
            pindah.putExtra("DETAILFILM", it)
            startActivity(pindah)
        }

        rv_film.layoutManager = LinearLayoutManager(this)
        rv_film.adapter = adapterfilm


    }


    fun getDataFilm(){
        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModel.getFilmLLD().observe(this, Observer {
            if(it != null){
                adapterfilm.setDataFilmLFA(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModel.callFilmApi()
    }

    override fun onStart() {
        super.onStart()

        getDataFilm()
    }

}