package com.coufie.challengechapterfive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coufie.challengechapterfive.R
import com.coufie.challengechapterfive.model.GetFilmDataItem
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(private var onClick: (GetFilmDataItem)->Unit) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    private var dataFilmLFA : List<GetFilmDataItem>? = null

    fun setDataFilmLFA(film : List<GetFilmDataItem>){
        this.dataFilmLFA = film
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        val UiLFA = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film , parent, false)

        return ViewHolder(UiLFA)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.itemView.tv_judul.text = dataFilmLFA!![position].title
        holder.itemView.tv_tanggal.text = dataFilmLFA!![position].createdAt
        holder.itemView.tv_sutradara.text = dataFilmLFA!![position].director

        this.let {
            Glide.with(holder.itemView.context).load(dataFilmLFA!![position].image).into(holder.itemView.iv_image)
        }

        holder.itemView.cardFilm.setOnClickListener{
            onClick(dataFilmLFA!![position])
        }
    }

    override fun getItemCount(): Int {
        if(dataFilmLFA == null){
            return 0
        }else{
            return dataFilmLFA!!.size
        }
    }

}