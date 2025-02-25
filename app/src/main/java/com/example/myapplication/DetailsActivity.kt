package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityDetailsBinding
import com.example.myapplication.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityDetailsBinding.inflate(layoutInflater)
            setContentView(binding.root)

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_details)
            val film = intent.extras?.get("film") as Film
            binding.detailsToolbar.title = film.title
            binding.detailsPoster.setImageResource(film.poster)
            binding.detailsDescription.text = film.description
        }

    }
