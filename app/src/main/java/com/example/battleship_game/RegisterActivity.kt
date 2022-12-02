package com.example.battleship_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {

    lateinit var  btnRegister : Button
    lateinit var etEmail : EditText
    lateinit var etPassword : EditText

    val fbAuth = FirebaseAuth_Provider().fbAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister = findViewById(R.id.btnRegister)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPass)

        btnRegister.setOnClickListener(){
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

            if(pass.length < 8){
                etPassword.error = "La contraseña debe tener al menos 6 caracteres"
                etPassword.requestFocus()
                return@setOnClickListener
            }else{
                fbAuth.createUserWithEmailAndPassword(email.toString(), pass.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this,HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            etEmail.error = "Email ya registrado"
                            etEmail.requestFocus()
                        }
                    }
            }


        }
    }


}