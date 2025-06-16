package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.view.fragments.DetailsFragment
import com.example.myapplication.view.fragments.FavoritesFragment
import com.example.myapplication.view.fragments.HomeFragment
import com.example.myapplication.view.fragments.SelectionsFragment
import com.example.myapplication.view.fragments.SettingsFragment
import com.example.myapplication.view.fragments.WatchLaterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyApplication)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initNavigation()

        binding.topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Когда-нибудь здесь будет навигация...", Toast.LENGTH_SHORT).show()
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

        fun launchDetailsFragment(film: Film) {
            val bundle = Bundle()
            bundle.putParcelable("film", film)
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_placeholder, fragment)
                .addToBackStack(null)
                .commit()
        }

        private fun initNavigation() {


            binding.topAppBar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.button_setting -> {
                        Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }


            binding.bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        val tag = "home"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment ?: HomeFragment(), tag)
                        true
                    }

                    R.id.favorites -> {
                        val tag = "favorites"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment ?: FavoritesFragment(), tag)
                        true
                    }

                    R.id.watch_later -> {
                        val tag = "watch_later"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment ?: WatchLaterFragment(), tag)
                        true
                    }
                    R.id.selections -> {
                        val tag = "selections"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment ?: SelectionsFragment(), tag)
                        true
                    }
                    R.id.settings -> {
                        val tag = "settings"
                        val fragment = checkFragmentExistence(tag)
                        changeFragment(fragment ?: SettingsFragment(), tag)
                        true
                    }


                    else -> false
                }
            }
        }
    private fun checkFragmentExistence(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)
    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment, tag)
            .addToBackStack(null)
            .commit()
    }


}

















