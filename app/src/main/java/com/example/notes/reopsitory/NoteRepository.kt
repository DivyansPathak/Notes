package com.example.notes.reopsitory

import androidx.room.Query
import com.example.notes.database.NoteDatabase
import com.example.notes.model.Note

class NoteRepository(private val db : NoteDatabase) {

    suspend fun insertNote(note : Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note : Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note : Note) = db.getNoteDao().updateNote(note)

    fun getAllNote() = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}