package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        actionBar?.hide();

        var tvRegistrarse = findViewById<TextView>(R.id.tvRegistrarse)
        var etEmail = findViewById<EditText>(R.id.evEmail)
        var etPassword = findViewById<EditText>(R.id.etPass)
        var btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            etEmail.error = null
            etPassword.error = null

            val email = etEmail.text
            val pass = etPassword.text

            if (pass.isNullOrBlank() && email.isNullOrBlank()){
                etEmail.error = "Inserte Email"
                etPassword.error = "Inserte Contraseña"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (email.isNullOrBlank()){
                etEmail.error = "Inserte Email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if (pass.isNullOrBlank()){
                etPassword.error = "Inserte Contraseña"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if (email.isNotBlank() && pass.isNotBlank()){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }



        }

        tvRegistrarse.setOnClickListener(){
            Toast.makeText(this@LoginActivity, "Aun no hay pantalla de registro :(", Toast.LENGTH_SHORT).show()
        }
    }
}


