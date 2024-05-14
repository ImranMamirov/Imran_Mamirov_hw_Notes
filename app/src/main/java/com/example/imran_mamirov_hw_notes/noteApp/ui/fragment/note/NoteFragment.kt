package com.example.imran_mamirov_hw_notes.noteApp.ui.fragment.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imran_mamirov_hw_notes.R
import com.example.imran_mamirov_hw_notes.databinding.FragmentNoteBinding
import com.example.imran_mamirov_hw_notes.noteApp.utils.SharedPreferenceHelper

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() = with(binding){
//        val sharedPreferenceHelper = SharedPreferenceHelper()
//        sharedPreferenceHelper.unit(requireContext())
    }
}