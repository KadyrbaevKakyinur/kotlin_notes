package com.example.kotlin_notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

open class NoteAdapter(private  val listener: IOnItem) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val list : MutableList<NoteModel> = ArrayList()



    fun setList(list: MutableList<NoteModel>) {
        this.list .clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }


    fun addNote(note: NoteModel){
        list.add(note)
        notifyDataSetChanged()
    }


    fun delete(pos: Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun getItem(pos:Int):NoteModel{
        return list[pos]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        return ViewHolder(view)
    }



    inner  class ViewHolder (itemView : View):RecyclerView.ViewHolder(itemView){
        private  val imageView: ImageView =itemView.findViewById(R.id.item_image)
        private  val title : TextView = itemView.findViewById(R.id.item_title)
        private  val  desc : TextView = itemView.findViewById(R.id.item_desc)
        private  val  delete :ImageView = itemView.findViewById(R.id.item_delete)
        private  val  pen : ImageView = itemView.findViewById(R.id.item_pen)
        private val share : ImageView = itemView.findViewById(R.id.share)


        fun bind(pos: Int){
            title.text = list[position].title
            desc.text= list[position].desc
            Glide.with(itemView)
                .load(list[position].image)
                .transform(CenterCrop(), RoundedCorners(25))
                .into(imageView)

            delete.setOnClickListener{
                listener.delete(adapterPosition)
            }

            pen.setOnClickListener {
                listener.edit(adapterPosition, list[position]) }


            share.setOnClickListener{
                listener.share(adapterPosition)
            }
        }




    }

    interface IOnItem{
        fun  delete(pos:Int)
        fun edit(pos:Int, note: NoteModel)
        fun share (pos:Int)
    }

}