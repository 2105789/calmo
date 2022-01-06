package com.calmox.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.calmox.Model.NotesModel
import com.calmox.Model.NotesViewModel
import com.calmox.R
import com.calmox.databinding.FragmentNotesAddBinding
import java.text.SimpleDateFormat
import java.util.*

class NotesAddFragment : Fragment() {
    lateinit var bind : FragmentNotesAddBinding
    lateinit var  notesViewModel : NotesViewModel
    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentNotesAddBinding.inflate(layoutInflater)

        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val id = sdf.format(Date()).toString()

        Log.d("Notes_ID", id)

        bind.ButtonAdd.setOnClickListener{
            if(bind.notesTitle.text.toString().trim() == "" || bind.notesData.text.toString().trim() == ""){
                Toast.makeText(context, "fields can't be empty", Toast.LENGTH_SHORT).show()
            }else {
                val data =
                    NotesModel(id, bind.notesTitle.text.toString(), bind.notesData.text.toString())
                notesViewModel.insert(data)
                findNavController().navigate(R.id.gotoFeedNote)
            }
        }
        return bind.root
    }

}