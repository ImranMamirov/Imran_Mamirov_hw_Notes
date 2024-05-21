package com.example.imran_mamirov_hw_notes.noteApp.Interface

import com.example.imran_mamirov_hw_notes.noteApp.data.models.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}