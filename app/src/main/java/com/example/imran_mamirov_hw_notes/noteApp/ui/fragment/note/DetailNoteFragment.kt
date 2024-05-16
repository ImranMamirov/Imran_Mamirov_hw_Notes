package com.example.imran_mamirov_hw_notes.noteApp.ui.fragment.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imran_mamirov_hw_notes.databinding.FragmentDetailNoteBinding
import com.example.imran_mamirov_hw_notes.noteApp.data.extension.setBackStackData

class DetailNoteFragment : Fragment() {

    private lateinit var binding: FragmentDetailNoteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.btnAdd.setOnClickListener{
            val et = binding.etAdd.text.toString()
            setBackStackData("key", et, true)
        }
    }
}