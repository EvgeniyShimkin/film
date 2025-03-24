package com.example.myapplication

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FilmItemBinding

class FilmViewHolder (val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description

    fun bind(film: Film){
        title.text = film.title
        poster.setImageResource(film.poster)




        description.text = film.description
    }


}