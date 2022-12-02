package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var tvRegistrarse = findViewById<TextView>(R.id.tvRegistrarse)
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPass)
        var btnLogin = findViewById<Button>(R.id.btnRegister)

        var fbAuth = FirebaseAuth_Provider().fbAuth

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

            fbAuth.signInWithEmailAndPassword(email.toString(), pass.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this,HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        etEmail.error = "Email o Contraseña Incorrectos"
                        etPassword.error = "Email o Contraseña Incorrectos"
                        etEmail.requestFocus()
                    }
                }

        }

        tvRegistrarse.setOnClickListener(){
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        }
    }
}


