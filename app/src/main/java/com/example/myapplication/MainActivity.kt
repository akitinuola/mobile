package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var signUpButton: Button
    lateinit var etloginemail: EditText
    lateinit var etloginpassword: EditText
    private lateinit var auth: FirebaseAuth
// Initialize Firebase Auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()

        button = findViewById(R.id.btnLogin)
        etloginemail = findViewById(R.id.etloginEmail)
        etloginpassword = findViewById(R.id.etloginpassword)
        auth = Firebase.auth

        button.setOnClickListener {

            if (etloginemail.text.isEmpty() || etloginpassword.text.isEmpty()) {
                Toast.makeText(
                    baseContext, "Text field cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth.signInWithEmailAndPassword(
                    etloginemail.text.toString(),
                    etloginpassword.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            editor.putString("email", user?.email)
                            println(user?.displayName)
                            editor.commit()
                            var intent = Intent(this, HomepageActivity::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            // If sign in fails, display a message to the
                            // user.

                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

        }

        signUpButton = findViewById(R.id.btnloginsignup)

        signUpButton.setOnClickListener {
            var intent = Intent(this, signup::class.java)

            startActivity(intent)
        }


    }

}