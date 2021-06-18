package com.example.takeanote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adaptor = NotesRVAdapter(this, this)
        recyclerView.adapter = adaptor
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            it?.let {
                adaptor.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.editableText.toString()
        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "Cannot Make Empty Note $noteText", Toast.LENGTH_LONG).show()
        }
    }
}