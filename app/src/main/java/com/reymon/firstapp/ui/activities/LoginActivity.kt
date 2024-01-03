package com.reymon.firstapp.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.R
import com.reymon.firstapp.logic.usercases.local.LoginUserCase
import com.reymon.firstapp.databinding.ActivityLoginBinding
import com.reymon.firstapp.core.Application
import com.reymon.firstapp.core.Constants


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginUserCase: LoginUserCase = LoginUserCase(Application.getConnectionDB()!!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initControls()
        returnMain()

    }

    override fun onStart(){
        super.onStart()


    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun initControls(){
        binding.btnLoginApp.setOnClickListener {
            val check = LoginUserCase(Application.getConnectionDB()!!).checkUserandPassword(binding.etUser.text.toString(),
                binding.etPassword.text.toString())
            if(check > 0){
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(Constants.USER_ID, check)
                startActivity(intent)
            }
            else{
                Snackbar.make(binding.btnLoginApp,
                    "Usuario o contraseña incorrecto",
                    Snackbar.LENGTH_SHORT).show()
                binding.etUser.text.clear()
                binding.etPassword.text.clear()
            }

        }
    }
    fun returnMain(){
        binding.returnMainActivity.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}