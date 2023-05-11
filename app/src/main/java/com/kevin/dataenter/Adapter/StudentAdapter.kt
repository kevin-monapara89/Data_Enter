package com.kevin.dataenter.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.dataenter.Model.StudenModel
import com.kevin.dataenter.R

class StudentAdapter(click: (Int) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    var click = click
    lateinit var list : ArrayList<StudenModel>

    class StudentHolder(itemView: View) : ViewHolder(itemView){
        var id = itemView.findViewById<TextView>(R.id.txtid)
        var name = itemView.findViewById<TextView>(R.id.txtname)
        var surname = itemView.findViewById<TextView>(R.id.txtsurname)
        var std = itemView.findViewById<TextView>(R.id.txtstd)
        var delete = itemView.findViewById<ImageView>(R.id.delete)
        var update = itemView.findViewById<ImageView>(R.id.update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.itemview,parent,false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.id.text = list.get(position).id.toString()
        holder.name.text = list.get(position).name
        holder.surname.text = list.get(position).surname
        holder.std.text = list.get(position).std

        holder.delete.setOnClickListener {
            click.invoke(list.get(position).id)
        }

        holder.update.setOnClickListener {

        }
    }

    fun update(students: ArrayList<StudenModel>) {
        list = students
        notifyDataSetChanged()
    }

    fun setStudents(list: ArrayList<StudenModel>) {
        this.list = list
    }
}