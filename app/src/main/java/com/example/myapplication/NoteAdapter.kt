package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val notes: ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var sharedPreference: SharedPreferences? = null
        var title: TextView
        var description: TextView
        var cardView: CardView

        init {
            title = item.findViewById(R.id.tvTitle)
            description = item.findViewById(R.id.tvDesciption)
            cardView = item.findViewById(R.id.cvNoteItem)

            cardView.setOnClickListener {

                val intent = Intent(item.context, NoteActivity::class.java)
                intent.putExtra("title", title.text)
                intent.putExtra("description", description.text)
                item.context.startActivity(intent)


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.notes_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.description.text = notes[position].description
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}