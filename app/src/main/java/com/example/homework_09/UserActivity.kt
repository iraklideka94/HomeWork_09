package com.example.homework_09

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_09.databinding.ActivityMainBinding


class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)
        binding.mRecycler.layoutManager = LinearLayoutManager(this)
        binding.mRecycler.adapter = userAdapter
        binding.addBtn.setOnClickListener { addInfo() }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item, null)

        val userNo = v.findViewById<EditText>(R.id.userLname)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userEmail = v.findViewById<EditText>(R.id.userEmail)
        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val lastname = userNo.text.toString()
            val email = userEmail.text.toString()
            userList.add(UserData("Name: $names", "Lastname : $lastname", "Email : $email"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }
}