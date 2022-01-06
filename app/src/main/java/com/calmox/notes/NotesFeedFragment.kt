package com.calmox.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.calmox.Adapter.NotesAdapter
import com.calmox.Model.NotesViewModel
import com.calmox.R
import com.calmox.databinding.FragmentNotesFeedBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NotesFeedFragment : Fragment() {
    lateinit var bind : FragmentNotesFeedBinding
    lateinit var notesViewModel : NotesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentNotesFeedBinding.inflate(inflater, container, false)
        notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        bind.ButtonWrite.setOnClickListener{
            findNavController().navigate(R.id.gotoAddNotes)
        }
        val adapter = NotesAdapter()
        val recyclerView = bind.notesRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        notesViewModel.readalldata.observe(viewLifecycleOwner, Observer {
            update->
            adapter.setData(update)
        })

        bind.ButtonWrite.setOnClickListener {
            findNavController().navigate(R.id.gotoAddNotes)
        }
        bind.ButtonDelete.setOnClickListener{
            showAleartDialog(bind.root)
        }

        return bind.root
    }

    fun showAleartDialog(view: View){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("delete all notes")
            .setMessage("once deleted notes cannot be recovered\nwanna still continue?")
            .setPositiveButton("no"){dialog,which ->
            }
            .setNegativeButton("yes"){dialog,which ->
                notesViewModel.deleteall()
            }
            .show()
    }

}