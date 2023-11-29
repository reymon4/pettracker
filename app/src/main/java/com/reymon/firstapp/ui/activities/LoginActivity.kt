package com.reymon.firstapp.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.reymon.firstapp.R
import com.reymon.firstapp.logic.usecases.SingIn
import com.reymon.firstapp.databinding.ActivityLoginBinding
import com.reymon.firstapp.ui.core.Constants


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val singIn: SingIn = SingIn()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        //regreso()
    }

    override fun onStart() {
        super.onStart()


    }

    //    fun initControls(){
//
//        binding.btnLogin.setOnClickListener{
//            var u = binding.etUser.text.toString()
//            var p = binding.etPassword.text.toString()
//            var conect = singIn.checkUserPassword(u,p)
//            //Toast.makeText(this, u, Toast.LENGTH_SHORT).show()
//            if(!conect)
//                Snackbar.make(binding.btnLogin, "Incorrecto", Snackbar.LENGTH_LONG).show() else{
//                Snackbar.make(binding.btnLogin, "Usuario Correcto", Snackbar.LENGTH_LONG).show()
//                val intent_impl = Intent().apply {
//                    action = Intent.ACTION_SEND //forma de enviar valores a travez del intent ↓
//                    putExtra(Intent.EXTRA_TEXT, "Este es el valor que comparto" )
//                    type = "text/plain"
//                }
//                startActivity(intent_impl)
//
//                val intent_exp = Intent(this, MainActivity::class.java)
//                intent_exp.putExtra(Intent.EXTRA_TEXT, "usuario")
//                startActivity(intent_exp)
//            }
//
//
//        }
//    }
    private fun initListeners() {
        binding.btnLoginApp.setOnClickListener {
            val check = SingIn().checkUserPassword(
                binding.etUser.text.toString(), //Aquí poner el nombre del input de usuario (String)
                binding.etPassword.text.toString() //Aquí ponemos la etiqueta de la contraseña
            )
            if (check > 0) {
                val intent =
                    Intent(this, HomeActivity::class.java) //Verificar a qué layout voy a mandar
                intent.putExtra(Constants.USER_ID, check)
            } else {
                Snackbar.make(
                    binding.etUser, "Usuario o contraseña incorrectos", Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
    fun regreso() {
        binding.returnMainActivity.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}