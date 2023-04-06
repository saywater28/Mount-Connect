package com.example.mountconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var emailUp : EditText
    private lateinit var passUp : EditText
    private lateinit var signUpBtn : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = Firebase.auth

        emailUp = findViewById(R.id.emailUp)
        passUp = findViewById(R.id.passUp)
        signUpBtn = findViewById(R.id.signUpBtn)

        signUpBtn.setOnClickListener {
            val email = emailUp.text.toString()
            val password = passUp.text.toString()

            signUpBtn(email, password)

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun signUpBtn(email: String, password: String) {
        // logic of creating user
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                } else {
                    // If sign in fails, display a message to the user.

                }
            }

    }
}