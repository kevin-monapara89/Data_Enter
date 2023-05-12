package com.kevin.dataenter

import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.dataenter.Adapter.StudentAdapter
import com.kevin.dataenter.Model.StudenModel
import com.kevin.dataenter.databinding.ActivityMainBinding
import com.kevin.dataenter.databinding.UpdateBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var bind: UpdateBinding
    lateinit var dbHelper: DbHelper
    lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DbHelper(this)
        var list = dbHelper.getStudents()

        adapter = StudentAdapter ({ id ->

            var dialog = AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are You Sure To Delete?")
                .setPositiveButton("Yes", object : OnClickListener {

                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        dbHelper.deleteStudent(id)
                        adapter.update(dbHelper.getStudents())
                    }
                })
                .setNegativeButton("No", object : OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                    }
                })
                .create()
            dialog.show()
        },{
            var dialog = Dialog(this)
            var bind = UpdateBinding.inflate(layoutInflater)
            dialog.setContentView(bind.root)

            var id = it.id
            bind.edtname.setText(it.name)
            bind.edtsurname.setText(it.surname)
            bind.edtstd.setText(it.std)

            bind.btnupdate.setOnClickListener {
                var n = bind.edtname.text.toString()
                var sn = bind.edtname.text.toString()
                var st = bind.edtstd.text.toString()
                var md = StudenModel(id, n, sn, st)
                dbHelper.updateStudent(md)
                adapter.update(dbHelper.getStudents())
                dialog.dismiss()
            }
            dialog.show()
        })

        adapter.setStudents(list)
        binding.rcvlist.layoutManager = LinearLayoutManager(this)
        binding.rcvlist.adapter = adapter

        binding.btnsubmit.setOnClickListener {

            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()
            var std = binding.edtstd.text.toString()

            if (name.isEmpty() || surname.isEmpty() || std.isEmpty()) {
                Toast.makeText(this, "Please Enter Data.......", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Data Add Succesfullly", Toast.LENGTH_SHORT).show()
                var data = StudenModel(0, name, surname, std)
                dbHelper.addStudent(data)
                adapter.update(dbHelper.getStudents())
                clearEditText()
            }
        }
    }

    private fun clearEditText() {
        binding.edtname.setText("")
        binding.edtsurname.setText("")
        binding.edtstd.setText("")
    }
}
