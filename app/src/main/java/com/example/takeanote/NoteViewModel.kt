package com.example.takeanote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getDao()
        val repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }
}