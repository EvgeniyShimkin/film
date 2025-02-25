package com.example.myapplication

import android.content.Intent
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    val filmsDataBase = listOf(
        Film(
            "Капитан Америка",
            R.drawable.film_poster1,
            "Сюжет разворачивается после фильма «Вечные» и сериала «Сокол и Зимний Солдат». Группа наёмников похищает образец металла Адамантий, добытого из тела Целестиала Тиамута. Но их останавливают новый Капитан Америка (Энтони Маки) и его помощник Сокол. Сразу после этих событий и неудачного покушения на нового президента США, коим стал бывший генерал Таддеус «Громовержец» Росс, начинают происходить загадочные события, коим из-за кулис руководит неизвестный кукловод. "
        ),
        Film(
            "Побег из Шоушенка",
            R.drawable.film_poster2,
            "Сюжет: успешный банкир Энди Дюфрейн оказывается главным обвиняемым в двойном убийстве. Одним вечером полиция обнаруживает тела его жены с любовником, а сам Энди просто не может вспомнить, что делал в ночь трагедии. Так он оказывается в самой суровой тюрьме Новой Англии, где ему предстоит провести оставшуюся жизнь.Вежливому и кроткому Энди непросто приспособиться к жестокой тюремной реальности, где опасность исходит не только от заключённых, но и от администрации. Но со временем Энди удаётся завести друзей, а его профессиональные качества талантливого банкира очень пригождаются, когда он начинает оказывать сотрудникам Шоушенка финансовые и юридические консультации."
        ),
        Film(
            "Форрест Гамп",
            R.drawable.film_poster3,
            " Сидя на автобусной остановке, Форрест Гамп — не очень умный, но добрый и открытый парень — рассказывает случайным встречным историю своей необыкновенной жизни. С самого малолетства парень страдал от заболевания ног, соседские мальчишки дразнили его, но в один прекрасный день Форрест открыл в себе невероятные способности к бегу. Подруга детства Дженни всегда его поддерживала и защищала, но вскоре дороги их разошлись."
        ),
        Film(
            "Бойцовский клуб",
            R.drawable.film_poster4,
            "Сотрудник страховой компании страдает хронической бессонницей и отчаянно пытается вырваться из мучительно скучной жизни. Однажды в очередной командировке он встречает некоего Тайлера Дёрдена — харизматического торговца мылом с извращенной философией. Тайлер уверен, что самосовершенствование — удел слабых, а единственное, ради чего стоит жить, — саморазрушение. Проходит немного времени, и вот уже новые друзья лупят друг друга почем зря на стоянке перед баром, и очищающий мордобой доставляет им высшее блаженство. Приобщая других мужчин к простым радостям физической жестокости, они основывают тайный Бойцовский клуб, который начинает пользоваться невероятной популярностью."
        ),
        Film(
            "Властелин колец: Две крепости",
            R.drawable.film_poster5,
            "Братство распалось, но Кольцо Всевластья должно быть уничтожено. Фродо и Сэм вынуждены довериться Голлуму, который взялся провести их к вратам Мордора. Громадная армия Сарумана приближается: члены братства и их союзники готовы принять бой. Битва за Средиземье продолжается."
        ),
        Film(
            "Матрица",
            R.drawable.film_poster6,
            "Жизнь Томаса Андерсона разделена на две части: днём он — самый обычный офисный работник, получающий нагоняи от начальства, а ночью превращается в хакера по имени Нео, и нет места в сети, куда он бы не смог проникнуть. Но однажды всё меняется. Томас узнаёт ужасающую правду о реальности."
        ),
        Film(
            "Интерстеллар",
            R.drawable.film_poster7,
            "Когда засуха, пыльные бури и вымирание растений приводят человечество к продовольственному кризису, коллектив исследователей и учёных отправляется сквозь червоточину (которая предположительно соединяет области пространства-времени через большое расстояние) в путешествие, чтобы превзойти прежние ограничения для космических путешествий человека и найти планету с подходящими для человечества условиями."
        ),
        Film(
            "Унесённые призраками",
            R.drawable.film_poster8,
            "Тихиро с мамой и папой переезжает в новый дом. Заблудившись по дороге, они оказываются в странном пустынном городе, где их ждет великолепный пир. Родители с жадностью набрасываются на еду и к ужасу девочки превращаются в свиней, став пленниками злой колдуньи Юбабы. Теперь, оказавшись одна среди волшебных существ и загадочных видений, Тихиро должна придумать, как избавить своих родителей от чар коварной старухи."
        ),
        Film(
            "1+1",
            R.drawable.film_poster9,
            "Сюжет: в результате несчастного случая богатый бизнесмен Филипп становится инвалидом. Помогая ему адаптироваться, врачи направляют к нему работников по уходу за больными. Из всех кандидатов на роль медбрата привередливый богач выбирает самого странного: молодого африканского эмигранта с судимостью. Непосредственный араб становится для скучающего Филиппа и ангелом-хранителем, и источником ежедневных приключений. Отношения двух мужчин из разных социальных слоёв перерастают в крепкую дружбу"
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        initNavigation1()


        binding.topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Когда-нибудь здесь будет навигация...", Toast.LENGTH_SHORT).show()
        }

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
                R.id.favorites -> {
                    Toast.makeText(this, "Избранное", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.watch_later -> {
                    Toast.makeText(this, "Посмотреть позже", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.selections -> {
                    Toast.makeText(this, "Рекомендации", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }


    }

    private fun initNavigation1() {
        binding.mainRecycler.apply {
            filmsAdapter = FilmListRecyclerAdapter(object :
                FilmListRecyclerAdapter.OnItemClickListener{
                    override fun click(film: Film) {
                        val bundle = Bundle()
                        bundle.putParcelable("film", film)

                        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                })
                        adapter = filmsAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        val decorator = TopSpacingItemDecoration(8)
                        addItemDecoration(decorator)
                    }
                    filmsAdapter.addItems(filmsDataBase)



        }
    }













