package com.example.myapplication.view.rv_adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.view.rv_viewholders.FilmViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.FilmItemBinding
import com.example.myapplication.domain.Film

class FilmListRecyclerAdapter(private val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Film>()
    private lateinit var binding: FilmItemBinding


    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmViewHolder(
            FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
                holder.binding.itemContainer.setOnClickListener{
                    clickListener.click(items[position])
                }//анимация перехода
                (holder.itemView as MotionLayout).setTransitionListener(object : MotionLayout.TransitionListener {
                    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                        if (currentId == R.id.expanded) {
                            clickListener.click(items[position])
                            motionLayout.transitionToStart()
                        }
                    }
                    override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {}
                    override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {}
                    override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {}
                })
            }
        }
            }


    fun addItems(list: List<Film>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    interface OnItemClickListener{
        fun click(film: Film)
    }

}














