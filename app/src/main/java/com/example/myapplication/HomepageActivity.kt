package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.io.InputStream

class HomepageActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var fab: FloatingActionButton
    var notes = ArrayList<Note>()

    var sharedPreference: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
      sharedPreference= getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        supportActionBar?.title = "Welcome, " + sharedPreference!!.getString("email", "Not set")
//        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#146775")))
        recyclerView = findViewById(R.id.homeRecyclerView)
        fab = findViewById(R.id.fabHome)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val inputStream: InputStream = assets.open("notes.json")
        var json: String? = inputStream.bufferedReader().use { it.readText() }
        var jsonarr = JSONArray(json)

        for (i in 0..jsonarr.length() - 1) {
            var jsonObj = jsonarr.getJSONObject(i)
            var note = Note(jsonObj.getString("title"), jsonObj.getString("description"))
            notes.add(note)


        }




        noteAdapter = NoteAdapter(notes)
        recyclerView.adapter = noteAdapter

        fab.setOnClickListener {
            val intent = Intent(this,NewNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        println("This is on resume")

    }

    override fun onRestart() {
        super.onRestart()

        if (sharedPreference!!.getString("noteTitle", "Not set") != "Not set"){
            var note = sharedPreference!!.getString("noteTitle", "Not set")
                ?.let { sharedPreference!!.getString("noteDescription", "Not set")
                    ?.let { it1 -> Note(it, it1) } }
            if (note != null) {
                notes.add(note)
                noteAdapter.notifyDataSetChanged()
            }
        }
//        if (sharedPreference!!.getString("noteTitle", "Not set") != "Not set"){
//            var note = sharedPreference!!.getString("noteTitle", "Not set")
//                ?.let { sharedPreference!!.getString("noteDescription", "Not set")
//                    ?.let { it1 -> Note(it, it1) } }
//            if (note != null) {
//                notes.add(note)
//            }
//        }
    }

    override fun onStart() {
        super.onStart()

        println("This is on start")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.darkMode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                true
            }
            R.id.lightMode -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}