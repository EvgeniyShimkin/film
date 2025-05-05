package com.example.myapplication.view.rv_viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FilmItemBinding
import com.example.myapplication.domain.Film

class FilmViewHolder (val binding: FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val title = binding.title
    private val poster = binding.poster
    private val description = binding.description



    private val ratingDonut = binding.ratingDonut

    fun bind(film: Film){
        title.text = film.title
        poster.setImageResource(film.poster)
        description.text = film.description
        //Устанавливаем рэйтинг
        ratingDonut.setProgress((film.rating * 10).toInt())
    }


}