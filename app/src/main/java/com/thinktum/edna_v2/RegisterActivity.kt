package com.thinktum.edna_v2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        btnRegisterReg.setOnClickListener {
            if(editEmailRegister.text?.trim().toString().isNotEmpty() || editPasswordRegister.text?.trim().toString().isNotEmpty()){
                //not empty email or password field
                Log.e("Register", "Email and password are not empty!")
                var email: String = editEmailRegister.text!!.trim().toString()
                var password: String = editPasswordRegister.text!!.trim().toString()
                createUser(email, password)
            } else {
                //empty email or password field
                Log.e("Register", "Either email or password were empty!")
            }
            Log.e("Action", "Register button was clicked")
        }
    }


    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email.trim(), password).addOnCompleteListener(this){task ->

            if(task.isSuccessful){
                Log.e("Task Message", "Task for registration successful...")

                val user = auth.currentUser
                val intent = Intent(this, DashboardActivity::class.java)

                intent.putExtra("UserID", user?.uid)
                startActivity(intent)
            }else{
                Log.e("Task Message", "Task for registration failed..."+task.exception)
            }

        }

    }
}