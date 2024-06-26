package com.example.imran_mamirov_hw_notes.noteApp.ui.fragment.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.imran_mamirov_hw_notes.R
import com.example.imran_mamirov_hw_notes.databinding.FragmentOnBoardBinding
import com.example.imran_mamirov_hw_notes.noteApp.ui.adapter.OnBoardViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListener()
        tabLayout()
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }

    private fun setUpListener() = with(binding.viewPager2) {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.tvSkip.visibility = View.INVISIBLE
                } else {
                    binding.tvSkip.visibility = View.VISIBLE
                }
                if (position == 2) {
                    binding.tvStart.visibility = View.VISIBLE
                } else {
                    binding.tvStart.visibility = View.INVISIBLE
                }
            }
        })

        binding.tvSkip.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2, true)
            }
        }

        binding.tvStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_singUpFragment)
        }
    }

    private fun tabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){ _, _ ->
        }.attach()
    }
}