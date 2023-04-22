package com.example.kotlin_notes

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() , NoteAdapter.IOnItem{

    private lateinit var addButon :Button
    private lateinit var  buttonSort : Button
    private lateinit var adapter :NoteAdapter
    private lateinit var searchEdit : EditText
    private lateinit var recyclerView: RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addButon = view.findViewById(R.id.add)
        buttonSort= view.findViewById(R.id.sort)
        searchEdit = view.findViewById(R.id.search_edit)
        recyclerView= view.findViewById(R.id.recycler)

        adapter= NoteAdapter(this)
        recyclerView.adapter= adapter

        adapter.setList((requireActivity() as MainActivity).list)


        addButon.setOnClickListener{
            val addFragment= AddFragment()
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container,addFragment).commit()
        }

        buttonSort.setOnClickListener{
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("sort")
            alertDialog.setMessage("You want sort by date or titile")
            alertDialog.setPositiveButton("date") {_: DialogInterface,_: Int ->

            }
            alertDialog.setNegativeButton("title"){_:DialogInterface?,_:Int->

            }
        }

    }

    override fun delete(pos: Int) {

    }

    override fun edit(pos: Int, note: NoteModel) {
    }

    override fun share(pos: Int) {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_main,container,false)
    }
}