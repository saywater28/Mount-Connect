package com.example.mountconnect

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var emailUp : EditText
    private lateinit var passUp : EditText
    private lateinit var signUpBtn : Button
//    private lateinit var loginBtn : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var Dbref : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        auth = Firebase.auth

        editName =findViewById(R.id.editName)
        emailUp = findViewById(R.id.emailUp)
        passUp = findViewById(R.id.passUp)
        signUpBtn = findViewById(R.id.signUpBtn)
//        loginBtn = findViewById(R.id.loginBtn)

        signUpBtn.setOnClickListener {
            val name = editName.text.toString()
            val email = emailUp.text.toString()
            val password = passUp.text.toString()

            signUpBtn(name, email, password)
        }

//        loginBtn.setOnClickListener {
//            val intent = Intent(this, SignIn::class.java)
//            startActivity(intent)
//        }

    }

    private fun signUpBtn(name: String, email: String, password: String) {
        // logic of creating user
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@SignUp, ChatList::class.java)
                    finish()
                    startActivity(intent)
                    addUserToDatabase(name, email, auth.currentUser?.uid!!)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show()
                }
            }
     }

    @SuppressLint("RestrictedApi")
    private fun addUserToDatabase(name: String, email: String, uid: String){
        Dbref = FirebaseDatabase.getInstance().getReference()
        Dbref.child("user").child(uid).setValue(User(email))
    }

}