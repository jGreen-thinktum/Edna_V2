package com.thinktum.edna_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnLoginLog.setOnClickListener {
            if(editEmailLogin.text?.trim().toString().isNotEmpty() || editPasswordLogin.text?.trim().toString().isNotEmpty()){
                //not empty email or password field
                Log.e("Login", "Email and password are not empty!")
                var email: String = editEmailLogin.text!!.trim().toString()
                var password: String = editPasswordLogin.text!!.trim().toString()
                signInUser(email, password)
            } else {
                //empty email or password field
                Log.e("Login", "Either email or password were empty!")
            }
            Log.e("Action", "Login button was clicked")
        }
    }


    fun signInUser(email:String, password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->

            if(task.isSuccessful){
                val user = auth.currentUser
                val intent = Intent(this, DashboardActivity::class.java)

                intent.putExtra("UserID", user?.uid)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Error !!"+task.exception, Toast.LENGTH_LONG).show()
            }
        }


    }
}