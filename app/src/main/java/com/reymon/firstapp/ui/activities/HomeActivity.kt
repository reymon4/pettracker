package com.reymon.firstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.R
import com.reymon.firstapp.logic.usecases.SingIn
import com.reymon.firstapp.ui.core.Constants
import com.reymon.firstapp.databinding.ActivityHomeBinding
import com.reymon.firstapp.ui.fragments.ListFragment1
import com.reymon.firstapp.ui.fragments.ListFragment2

//Tengo que crear un nuevo activity, ordenar los id (Strings)
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding//Crear este activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listFragment  = ListFragment1()
        val favoritesFragment  =ListFragment2()
        intent.extras.let {
            val userId = it?.getInt(Constants.USER_ID)
            if(userId != null) {
                val user = SingIn().getUserName(userId)
                binding.txtUserName.text =
                    "Bienvenido " + user.firstName.toString() + " " + user.lastName.toString()


                binding.bottomNavigation.setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.it_home -> {
                            val transaction = supportFragmentManager.beginTransaction()
                            transaction.replace(binding.frmContainer.id, listFragment)
                            transaction.commit()
                            true
                        }

                        R.id.it_fav -> {
                            val transaction = supportFragmentManager.beginTransaction()
                            transaction.replace(binding.frmContainer.id, favoritesFragment)
                            transaction.commit()
                            true
                        }

                        else -> false
                    }
                }
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