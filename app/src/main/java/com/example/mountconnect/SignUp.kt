package com.example.mountconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SignUp : AppCompatActivity() {

    private lateinit var emailUp : EditText
    private lateinit var passUp : EditText
    private lateinit var signUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailUp = findViewById(R.id.emailUp)
        passUp = findViewById(R.id.passUp)
        signUpBtn = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}