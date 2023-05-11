package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson

class NewNoteActivity : AppCompatActivity() {
    lateinit var noteTitle: EditText
    lateinit var noteDescription: EditText
    lateinit var button: Button

    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

//    editor.putString("key", "value")
//    editor.apply()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        noteTitle = findViewById(R.id.etNewNoteTitle)
        noteDescription = findViewById(R.id.etNewNoteDescription)

        sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        editor = sharedPreferences!!.edit()

        button = findViewById(R.id.butCreateNote)

        button.setOnClickListener {

//
//            var titles = ArrayList<String>()
//            var descriptions = ArrayList<String>()
//
//            titles.add(noteTitle.text.toString())
//            descriptions.add(noteDescription.text.toString())
//
//            var convertedTitle: String = Gson().toJson(titles)
//            var convertedDescription: String = Gson().toJson(descriptions)

            editor!!.putString("noteTitle", noteTitle.text.toString())

            editor!!.putString("noteDescription", noteDescription.text.toString())

            editor!!.apply()
        }


    }
}