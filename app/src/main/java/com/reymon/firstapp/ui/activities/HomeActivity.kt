package com.reymon.firstapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.R
import com.reymon.firstapp.data.local.entities.Users
import com.reymon.firstapp.logic.usercases.local.LoginUserCase
import com.reymon.firstapp.databinding.ActivityHomeBinding
import com.reymon.firstapp.ui.adapters.UsersAdapter
import com.reymon.firstapp.core.Application
import com.reymon.firstapp.core.Constants
import com.reymon.firstapp.logic.usercases.jikan.JikanGetTopAnimesUserCase
import com.reymon.firstapp.ui.adapters.TopAnimesAdapter
import com.reymon.firstapp.ui.fragments.ListFragment1
import com.reymon.firstapp.ui.fragments.ListFragment2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding//Crear este activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        checkDB()
        //initRecyclerView()
        returnLogin()
        getAllTopAnimes()
        initRecyclerView1()


    }

    private fun getAllTopAnimes() {
//        lifecycleScope.launch (Dispatchers.IO) {
//            val x =JikanGetTopAnimesUserCase().getResponse()
//            Log.d(Constants.TAG, x.data[0].toString())
        binding.animationView.visibility = View.VISIBLE

    }

    private fun initRecyclerView1() {
        lifecycleScope.launch(Dispatchers.Main) {

            binding.animationView.visibility = View.VISIBLE
            val jikan = JikanGetTopAnimesUserCase()
            val animes = withContext(Dispatchers.IO) { jikan.getResponse() }

            animes.onSuccess { animes ->
                val adapter = TopAnimesAdapter(animes.data)
                binding.rvUsers.adapter = adapter
                binding.rvUsers.layoutManager = LinearLayoutManager(
                    this@HomeActivity, LinearLayoutManager.VERTICAL, false
                )

            }
            animes.onFailure {
                //Entonces, aquí decidimos cómo manejar los errores en la UI
            }
            binding.animationView.visibility = View.GONE

        }
    }


    private fun initRecyclerView() {
        lifecycleScope.launch(Dispatchers.Main) {

            binding.animationView.visibility = View.VISIBLE
            val usrs = withContext(Dispatchers.IO) { getUsersList() }
            Log.d("MyApp", "usuarios:${usrs.size}")
            val adapter = UsersAdapter(usrs)
            binding.rvUsers.adapter = adapter
            binding.rvUsers.layoutManager = LinearLayoutManager(
                this@HomeActivity, LinearLayoutManager.VERTICAL, false
            )
            binding.animationView.visibility = View.GONE
        }
    }

    suspend private fun getUsersList(): List<Users> {
        delay(7000)
        return LoginUserCase(Application.getConnectionDB()!!).getAllUsers()

    }

    private fun checkDB() {
        lifecycleScope.launch(Dispatchers.Main) {
            val users = withContext(Dispatchers.IO) {
                getUsersList()
            }
            //Segunda forma de hacer lo mismo
//        lifecycleScope.launch(Dispatchers.IO) {
//            val users = LoginUserCase(Application.getConnectionDB()!!).getAllUsers()
//            withContext(Dispatchers.Main) {
//                users
//            }
        }
    }

    val s={x: Int, y:Int -> x+y}

    private fun initListeners() {
//        intent.extras.let {
//            val userId = it?.getInt(Constants.USER_ID)
//            if (userId != null) {
//                val user = SingIn(Application.getConnectionDB()!!).getUserName(userId)
//
//                binding.txtUserName.text =
//                    "Bienvenido " + user.firstName.toString() + " " + user.lastName.toString()

        binding.swipeRv.setOnRefreshListener {
            val adapter = TopAnimesAdapter(listOf())
            binding.rvUsers.adapter = adapter
            binding.rvUsers.layoutManager = LinearLayoutManager(
                this@HomeActivity, LinearLayoutManager.VERTICAL, false
            )
            initRecyclerView1()
            binding.swipeRv.isRefreshing = false
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.it_home -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, ListFragment1())
                    transaction.commit()
                    true
                }

                R.id.it_fav -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, ListFragment2())
                    transaction.commit()
                    true
                }

                else -> {
                    //UNA CORRUTINA DEBE ESTAR DENTRO DE UNA CORRUTINA
                    //Ciclo de vida de una corrutina enlazada al Main


                    false
                }
            }
        }
//            } else {
//                Snackbar.make(binding.txtUserName, "Ocurrio un error", Snackbar.LENGTH_SHORT).show()
//            }
//        }
//        returnLogin()
    }

    private fun correr() {  //Es decir, si la aplicación muere también muere la corrutina
        lifecycleScope.launch(Dispatchers.Main) {
            //Con este contexto puedo decir que voy a hacer una IO
            //CAmbio de main a IO, para saber qué es lo que se debe realizar
            //con el withContext ato ambientes
            val name = withContext(Dispatchers.IO) {
                val a = "Alexander"
                val b = a + "Beltrán"
                b
            }
            val w = withContext(Dispatchers.Default) {
                val listC = listOf(async { getName() }, async { getName() })
                val w = listC.awaitAll()
            }

            val name1 = withContext(Dispatchers.IO) {
                getName()
            }
            binding.txtUserName.text = name1

        }
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