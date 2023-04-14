package com.example.mountconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var loginBtn: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        auth = Firebase.auth

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            loginBtn(email, password)
        }

    }

    private fun loginBtn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@SignIn, ChatList::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }

    }
}