package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun onClickToast(view: View) {
        Toast.makeText(this, "Меню", Toast.LENGTH_SHORT).show()
    }
    fun onClickToast1(view: View) {
        Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
    }
    fun onClickToast2(view: View) {
        Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
    }
    fun onClickToast3(view: View) {
        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
    }
    fun onClickToast4(view: View) {
        Toast.makeText(this, "Выход", Toast.LENGTH_SHORT).show()
    }


}
