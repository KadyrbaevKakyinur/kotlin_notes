package com.example.kotlin_notes

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

private fun ImageView.setBackgroundColor(createFromPath: Drawable?) {

}

class AddFragment : Fragment() {
    private val GALLERY_REQUEST_CODE = 1
    private lateinit var addButton: Button
    private lateinit var title: EditText
    private lateinit var desc: EditText
    private lateinit var date: EditText
    private lateinit var imageView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_add, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = view.findViewById(R.id.edit_title)
        addButton = view.findViewById(R.id.save)
        desc = view.findViewById(R.id.edit_description)
        date = view.findViewById(R.id.edit_date)
        imageView = view.findViewById(R.id.image)


        /*    if (arguments != null) {
                val note = arguments?.getSerializable("edit") as NoteModel
                date.setText(note.date)
                desc.setText(note.desc)
                title.setText(note.title)
    */
        addButton.setOnClickListener {

            val des = desc.text.toString()
            val time = date.text.toString()
            val tit = title.text.toString()

            val newNote = NoteModel(
                "", tit, des, time
            )

            if (des.isEmpty() || tit.isEmpty() || time.isEmpty()) {
                Toast.makeText(requireContext(), "Please try again", Toast.LENGTH_SHORT).show()
            } else {
                (requireActivity() as MainActivity).list.add(newNote)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment()).commit()
            }
            /*  val newNote = NoteModel(
                  "${imageView}", "${title.text}",
                  "${desc.text}", "${date.text}"
              )

              if (desc.text.toString().isEmpty() || date.text.toString()
                      .isEmpty() || title.text.toString().isEmpty()
              ) {
                  Toast.makeText(
                      requireContext().applicationContext,
                      "the note mustn't be empty. Please fill and try again",
                      Toast.LENGTH_SHORT
                  ).show()

              } else {
                  (requireActivity() as MainActivity).list.add(newNote)
                  requireActivity().supportFragmentManager.beginTransaction()
                      .replace(R.id.main_container, MainFragment()).commit()
              }*/
        }
        //}

        if (arguments != null) {
            val textTitle = arguments?.getString("key")
            title.setText(textTitle)
        }

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageView = data!!.data as ImageView
            imageView.setBackgroundColor(Drawable.createFromPath(imageView.toString()))

        }
    }


}