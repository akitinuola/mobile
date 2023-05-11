package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signup : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var confirmPassword: EditText
    lateinit var fullName: EditText
    lateinit var number: EditText
    lateinit var signupButton: Button
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        email = findViewById(R.id.etSignupEmail)
        password = findViewById(R.id.etSignupPassword)
        confirmPassword = findViewById(R.id.etSignupConfirmPassword)
        fullName = findViewById(R.id.etSignupFullname)
        number = findViewById(R.id.etSignupNumber)
        signupButton = findViewById(R.id.btnSignup)
        auth = Firebase.auth

//do things if these 2 are correct.
        signupButton.setOnClickListener {
            if (email.text.isEmpty()|| password.text.isEmpty()) {
                Snackbar.make(
                    signupButton,
                    "Email and Password cannot be empty",
                    Snackbar.LENGTH_LONG
                ).show()
            } else if (password.text.toString() != confirmPassword.text.toString()) {
                Snackbar.make(
                    signupButton,
                    "Password not equal to Confirm Password",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val user = auth.currentUser
                            // Sign in success, update UI with the signed-in user's information


                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(
                                this, "${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }


    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
        }
    }
}