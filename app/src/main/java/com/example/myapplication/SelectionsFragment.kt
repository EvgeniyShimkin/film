package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentFavoritesBinding
import com.example.myapplication.databinding.FragmentSelectionsBinding

class SelectionsFragment : Fragment() {
    private var binding: FragmentSelectionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectionsBinding.inflate(inflater, container, false)
        return binding?.root
        //return inflater.inflate(R.layout.fragment_selections, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        // добавляем нашу анимацию

        binding?.let { AnimationHelper.performFragmentCircularRevealAnimation(it.selectionsFragmentRoot, requireActivity(), 4) }
    }



}