package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class NoteActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var title:String
    lateinit var description:String
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        textView = findViewById(R.id.tvNoteText)

        var intent = intent.extras
         title = intent?.getString("title").toString()

        supportActionBar?.title = title
      description = intent?.getString("description").toString()

        textView.text = description
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {

                val intent= Intent()
                intent.action=Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,description)
                intent.type="text/plain"
                startActivity(Intent.createChooser(intent,"Share To:"))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}