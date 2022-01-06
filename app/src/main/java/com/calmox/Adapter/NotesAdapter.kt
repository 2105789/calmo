package com.calmox.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calmox.Model.NotesModel
import com.calmox.databinding.NotesItemBinding

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.myviewholder>() {
    lateinit var bind : NotesItemBinding
    private var  NotesList = emptyList<NotesModel>()
    class myviewholder(bind : NotesItemBinding) : RecyclerView.ViewHolder(bind.root){
        val notesTitle = bind.notesTitle
        val notesData = bind.notesData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        bind = NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  myviewholder(bind)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        holder.notesTitle.text = NotesList[position].title
        holder.notesData.text = NotesList[position].data
    }

    override fun getItemCount(): Int = NotesList.size

    fun setData(data : List<NotesModel>){
     this.NotesList = data
     notifyDataSetChanged()
    }
}