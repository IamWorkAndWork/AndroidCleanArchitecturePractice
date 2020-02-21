package com.example.cleanarchitecturepractice.presentation.ListNote


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cleanarchitecturepractice.R
import kotlinx.android.synthetic.main.fragment_list.*

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListFragment : Fragment(),
    ListAction {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var notesListAdapter: NotesListAdapter
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesListAdapter =
            NotesListAdapter(
                arrayListOf(),
                this
            )

        notesListView.apply {
            layoutManager = LinearLayoutManager(activity!!)
            adapter = notesListAdapter
        }

        addNote.setOnClickListener {
            goToNoteDetails()
        }

        viewModel = ViewModelProviders.of(this)
            .get(ListViewModel::class.java)

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.notes.observe(activity!!, Observer { notesList ->
            loadingView.visibility = View.GONE
            notesListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(notesList.sortedByDescending {
                it.updateTime
            })
        })

    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action =
            ListFragmentDirections.actionGoToNote(
                id
            )
        Navigation.findNavController(notesListView).navigate(action)
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getNotes()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}
