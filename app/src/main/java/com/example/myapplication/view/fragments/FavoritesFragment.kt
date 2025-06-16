package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.view.rv_adapters.FilmListRecyclerAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.view.rv_adapters.TopSpacingItemDecoration
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.utils.AnimationHelper


class FavoritesFragment : Fragment() {
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding
    //private lateinit var favorites_recycler: FilmListRecyclerAdapter // добавил

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoritesList: List<Film> = emptyList()

        //добавляем анимацию
        _binding?.let { AnimationHelper.performFragmentCircularRevealAnimation(it.favoritesFragmentRoot, requireActivity(), 2) }

        binding?.favoritesRecycler
            ?.apply {
                filmsAdapter =
                    FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                        override fun click(film: Film) {
                            (requireActivity() as MainActivity).launchDetailsFragment(
                                film,

                            )
                        }
                    })
                //Присваиваем адаптер
                adapter = filmsAdapter
                //Присвои layoutmanager
                layoutManager = LinearLayoutManager(requireContext())
                //Применяем декоратор для отступов
                val decorator = TopSpacingItemDecoration(8)
                addItemDecoration(decorator)
            }
        //Кладем нашу БД в RV
        filmsAdapter.addItems(favoritesList)

    }
    }




