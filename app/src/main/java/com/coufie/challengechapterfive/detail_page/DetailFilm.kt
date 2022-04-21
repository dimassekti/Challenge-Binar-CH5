package com.coufie.challengechapterfive.detail_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.coufie.challengechapterfive.R
import com.coufie.challengechapterfive.model.GetFilmDataItem
import kotlinx.android.synthetic.main.activity_detail_film.*

class DetailFilm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        val detailFilm = intent.getParcelableExtra<GetFilmDataItem>("DETAILFILM")

        tv_film_director.text = detailFilm!!.director
        tv_film_release_date.text = detailFilm!!.createdAt
        tv_film_synopsis.text = detailFilm!!.synopsis
        tv_film_title.text = detailFilm!!.title
        Glide.with(this).load(detailFilm!!.image).into(iv_film_cover)
    }
}