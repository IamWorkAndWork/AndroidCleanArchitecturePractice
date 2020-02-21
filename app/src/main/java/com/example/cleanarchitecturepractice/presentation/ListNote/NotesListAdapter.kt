package com.example.cleanarchitecturepractice.presentation.ListNote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecturepractice.R
import com.example.core.data.Note
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotesListAdapter(
    var notes: ArrayList<Note>,
    private val actions: ListAction
) : RecyclerView.Adapter<NotesListAdapter.NoewViewHolder>() {

    companion object {
        val dateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm:ss")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoewViewHolder, position: Int) {
        holder.bindView(notes[position])
    }

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Note) {

            itemView.title.text = note.title
            itemView.description.text = note.content

//            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            itemView.update.text = "Last updated: ${dateFormat.format(resultDate)}"

            itemView.cardLayout.setOnClickListener { actions.onClick(note.id) }

            itemView.word_count.text = "Words : ${note.wordCount}"

        }

    }
}