//package com.example.imran_mamirov_hw_notes.noteApp.ui.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ListAdapter
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.imran_mamirov_hw_notes.databinding.ItemNoteBinding
//import com.example.imran_mamirov_hw_notes.noteApp.data.models.NoteModel
//
//class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.ViewHolder>(DiffCallBack()) {
//    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: NoteModel) {
//            binding.itemTv.text = item.title
//        }
//
//    }
//
//    class DiffCallBack : DiffUtil.ItemCallback<NoteModel>() {
//        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
//            return oldItem.title == newItem.title
//        }
//    }
//}