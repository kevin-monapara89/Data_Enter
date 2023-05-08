package com.kevin.dataenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        binding.btnsubmit.setOnClickListener {

            var name = binding.edtname.text.toString()
            var surname = binding.edtsurname.text.toString()
            var std = binding.edtstd.text.toString()

            if (name.isEmpty() || surname.isEmpty() || std.isEmpty()){
                Toast.makeText(this, "Please Enter Data.......", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "Data Add Succesfullly", Toast.LENGTH_SHORT).show()
                var data = StudenModel(0, name,  surname, std)
                dbHelper.addStudent(data)
                adapter.update(dbHelper.getStudents())
                clearEditText()
            }
        }

        var list = dbHelper.getStudents()
        adapter = StudentAdapter(list)
        binding.rcvlist.layoutManager = LinearLayoutManager(this)
        binding.rcvlist.adapter = adapter
    }

    private fun clearEditText() {
        binding.edtname.setText("")
        binding.edtsurname.setText("")
        binding.edtstd.setText("")
    }

}
