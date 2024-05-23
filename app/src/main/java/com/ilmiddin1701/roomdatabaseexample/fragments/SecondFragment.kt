package com.ilmiddin1701.roomdatabaseexample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ilmiddin1701.roomdatabaseexample.models.User
import com.ilmiddin1701.roomdatabaseexample.databinding.FragmentSecondBinding
import com.ilmiddin1701.roomdatabaseexample.db.AppDatabase

@Suppress("DEPRECATION")
class SecondFragment : Fragment() {
    private val binding by lazy { FragmentSecondBinding.inflate(layoutInflater) }
    private lateinit var appDatabase: AppDatabase
    private var user: User? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appDatabase = AppDatabase.getInstance(binding.root.context)
        try {
            user= arguments?.getSerializable("keyUser") as User
            editUser()
        }catch (e:Exception){
            addUser()
        }
        return binding.root
    }
    private fun addUser(){
        binding.apply {
            btnSave.setOnClickListener {
                val user = User(
                    edtName.text.toString(),
                    edtNumber.text.toString()
                )
                appDatabase.myDao().addUser(user)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
    private fun editUser(){
        binding.apply {
            edtName.setText(user?.name)
            edtNumber.setText(user?.number)
            btnSave.setOnClickListener {
                user?.name = edtName.text.toString()
                user?.number = edtNumber.text.toString()
                appDatabase.myDao().editUser(user!!)
                Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }
}