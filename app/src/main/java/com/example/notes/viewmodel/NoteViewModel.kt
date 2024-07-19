package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Query
import com.example.notes.model.Note
import com.example.notes.reopsitory.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app : Application,private val noteRepository: NoteRepository) : AndroidViewModel(app){

    fun addNote(note : Note) =
        viewModelScope.launch { // coroutine cancel when associated viewModel is cleared or destroyed to prevent potential memory leak
            noteRepository.insertNote(note)
        }
    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    fun getAllNote() = noteRepository.getAllNote()

    fun searchNote(query: String?) =
        noteRepository.searchNote(query)
}