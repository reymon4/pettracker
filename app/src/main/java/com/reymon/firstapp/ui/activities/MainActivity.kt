package com.reymon.firstapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reymon.firstapp.databinding.ActivityMainBinding
import com.creative.ipfyandroid.Ipfy
import com.creative.ipfyandroid.IpfyClass

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Aquí inicializamos el componente
        Ipfy.init(this) // this is a context of application
        //or you can also pass IpfyClass type to get either IPv4 address only or universal address IPv4/v6 as
        Ipfy.init(this, IpfyClass.IPv4) //to get only IPv4 address
        //and
        Ipfy.init(this,IpfyClass.UniversalIP) //to get Universal address in IPv
        getIpAdress()
        returnLogin()
    }
    private fun getIpAdress(){
        Ipfy.getInstance().getPublicIpObserver().observe(this, { ipData ->
            binding.txtIp.text=
                ipData.currentIpAddress // this is a value which is your current public IP address, null if no/lost internet connection

        })
    }

    fun returnLogin(){
        binding.btnLogIn.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


}