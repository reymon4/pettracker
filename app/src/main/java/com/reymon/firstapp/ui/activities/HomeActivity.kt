package com.reymon.firstapp.ui.activities

import android.content.Intent
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

        intent.extras.let {
            val userId = it?.getInt(Constants.USER_ID)
            if(userId != null){
                val  user = SingIn().getUserName(userId)
                binding.txtUserName.text = "Bienvenido " + user.firstName.toString() + " " + user.lastName.toString()
            }
            else{
                Snackbar.make(binding.txtUserName,"Ocurrio un error", Snackbar.LENGTH_SHORT).show()
            }
        }
        returnLogin()
    }

    fun returnLogin(){
        binding.returnLogin.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}