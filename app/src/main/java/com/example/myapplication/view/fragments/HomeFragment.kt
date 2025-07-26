package com.example.myapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.view.rv_adapters.FilmListRecyclerAdapter
import com.example.myapplication.MainActivity
import com.example.myapplication.view.rv_adapters.TopSpacingItemDecoration
import com.example.myapplication.databinding.HomeFragmentMotionSceneBinding
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.viewmodel.AutoDisposable
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import com.example.myapplication.viewmodel.addTo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class HomeFragment : Fragment() {
    //private lateinit var binding: MergeHomeScreenContentBinding
    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private val autoDisposable = AutoDisposable()
    private lateinit var binding: HomeFragmentMotionSceneBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private var filmsDataBase = listOf<Film>()
    //backing field

        set(value) {
            if (field == value) return
            field = value
            filmsAdapter.addItems(field)
        }

    //1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        autoDisposable.bindTo(lifecycle)
        retainInstance = true
    }

    //1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentMotionSceneBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // анимация
        val motionLayout = binding.root
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                binding.searchView.translationY = 0F
                binding.searchView.alpha = 1F
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })//фиксация чтобы при скролле не съехала

        binding.mainRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.searchView.translationY = 0f
                binding.searchView.alpha = 1f
            }
        })//включаем на стартовый экран при первом запуске
        motionLayout.post {
            motionLayout.transitionToEnd()
            binding.mainRecycler.isNestedScrollingEnabled = false
        }


        initPullToRefresh()
        initHomeFragment()
        initPullToRefresh()


        viewModel.filmsListData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { list ->
                filmsAdapter.addItems(list)
                filmsDataBase = list
            }
            .addTo(autoDisposable)
        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)
    }

private fun initPullToRefresh() {
    //Вешаем слушатель, чтобы вызвался pull to refresh
    binding.pullToRefresh.setOnRefreshListener {
        //Чистим адаптер(items нужно будет сделать паблик или создать для этого публичный метод)
        filmsAdapter.items.clear()
        //Делаем новый запрос фильмов на сервер
        viewModel.getFilms()
        //Убираем крутящиеся колечко
        binding.pullToRefresh.isRefreshing = false
    }
}

private fun initHomeFragment() {
    binding.mainRecycler.layoutManager = LinearLayoutManager(requireContext())
    filmsAdapter =
        FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
            override fun click(film: Film) {
                (requireActivity() as MainActivity).launchDetailsFragment(film)
            }
        })
    filmsAdapter.addItems(filmsDataBase)
    binding.mainRecycler.adapter = filmsAdapter
    val decorator = TopSpacingItemDecoration(8)
    binding.mainRecycler.addItemDecoration(decorator)

    binding.searchView.setOnClickListener {
        binding.searchView.isIconified = false
    }
    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            if (newText.isNullOrEmpty()) {
                filmsAdapter.addItems(filmsDataBase)
                return true
            }
            //Фильтруем список на поискк подходящих сочетаний
            val result = filmsDataBase.filter {
                //Чтобы все работало правильно, нужно и запрос, и имя фильма приводить к нижнему регистру
                it.title.lowercase(Locale.getDefault())
                    .contains(newText.lowercase(Locale.getDefault()))
            }
            //Добавляем в адаптер
            filmsAdapter.addItems(result)
            return true
        }
    })
}
}
//добавляем анимацию


