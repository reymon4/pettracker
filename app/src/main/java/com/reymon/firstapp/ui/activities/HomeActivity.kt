package com.reymon.firstapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.logic.usecases.SingIn
import com.reymon.firstapp.ui.core.Constants
import com.reymon.firstapp.databinding.HomeActivityBinding
//Tengo que crear un nuevo activity, ordenar los id (Strings)
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : HomeActivityBinding //Crear este activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent?.extras?.getInt(Constants.USER_ID)

        if (userId != null) {
            val user = SingIn().getUserName(userId) // Asumiendo que SingIn() es tu clase para obtener datos de usuario
            binding.txtUserName.text = user.firstName.toString()
        } else {
            Snackbar.make(binding.txtUserName, "Ocurrió un error", Snackbar.LENGTH_LONG).show()
        }
    }
}