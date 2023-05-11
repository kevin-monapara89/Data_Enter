package com.kevin.dataenter

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

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dbHelper: DbHelper
    lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DbHelper(this)
        var list = dbHelper.getStudents()

        adapter = StudentAdapter { id ->

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
        }

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
