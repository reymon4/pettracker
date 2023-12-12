package com.reymon.firstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.R
import com.reymon.firstapp.logic.usecases.SingIn
import com.reymon.firstapp.ui.core.Constants
import com.reymon.firstapp.databinding.ActivityHomeBinding
import com.reymon.firstapp.ui.core.Application
import com.reymon.firstapp.ui.fragments.ListFragment1
import com.reymon.firstapp.ui.fragments.ListFragment2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Tengo que crear un nuevo activity, ordenar los id (Strings)
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding//Crear este activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listFragment = ListFragment1()
        val favoritesFragment = ListFragment2()
//        intent.extras.let {
//            val userId = it?.getInt(Constants.USER_ID)
//            if (userId != null) {
//                val user = SingIn(Application.getConnectionDB()!!).getUserName(userId)
//
//                binding.txtUserName.text =
//                    "Bienvenido " + user.firstName.toString() + " " + user.lastName.toString()

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

                        else -> {
                            //UNA CORRUTINA DEBE ESTAR DENTRO DE UNA CORRUTINA
                            //Ciclo de vida de una corrutina enlazada al Main
                            //Es decir, si la aplicación muere también muere la corrutina
                            lifecycleScope.launch(Dispatchers.Main) {
                                //Con este contexto puedo decir que voy a hacer una IO
                                //CAmbio de main a IO, para saber qué es lo que se debe realizar
                                //con el withContext ato ambientes
                                val name = withContext(Dispatchers.IO) {
                                    val a = "Alexander"
                                    val b = a + "Beltrán"
                                    b
                                }
                                val s = async {
                                    val a = ""
                                }
                                val s1 = async {
                                    val a = ""
                                }
                                //También puedo enviar una lista a esta función
                                awaitAll(s,s1)
                                val name1 = withContext(Dispatchers.IO) {
                                    getName()
                                }
                                binding.txtUserName.text = name.toString()
                            }
                            false
                        }
                    }
                }
//            } else {
//                Snackbar.make(binding.txtUserName, "Ocurrio un error", Snackbar.LENGTH_SHORT).show()
//            }
//        }
        returnLogin()
    }

    fun returnLogin() {
        binding.returnLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    //Para que una función "funcione" dentro de un hilo tenemos que poner el suspend
    suspend fun getName(): String {
        val a = "Alexander"
        val b = a + "Beltrán"
        return b
    }

}