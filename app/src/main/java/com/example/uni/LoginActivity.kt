package com.example.uni

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth




class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var registerButtton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_screen)


        email = findViewById(R.id.email) as EditText
        password = findViewById(R.id.password) as EditText
        loginButton = findViewById(R.id.loginButton) as Button
        registerButtton = findViewById(R.id.registerButton) as Button

        auth = FirebaseAuth.getInstance()

        registerButtton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val userEmail = email.text.toString()
            val userPassword = password.text.toString()

            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        // Authentication successful, navigate to the main activity
                        val intentToMain = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intentToMain)
                    } else {
                        // Authentication failed, handle the error
                        // You can add error handling code here (e.g., show an error message)
                    }
                }
        }
    }
}