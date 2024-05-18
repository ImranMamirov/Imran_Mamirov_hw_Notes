package com.example.imran_mamirov_hw_notes.noteApp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imran_mamirov_hw_notes.noteApp.data.db.daos.NoteDao
import com.example.imran_mamirov_hw_notes.noteApp.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}