package com.thinktum.edna_v2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //https://www.youtube.com/watch?v=tnBt7RlLSlw

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check user

        auth = FirebaseAuth.getInstance()

        btnRegisterHome.setOnClickListener {
            Log.e("Register", "User selected to register as a user.")

            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLoginHome.setOnClickListener {
            Log.e("Login", "User selected to login as a user.")

            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if(user != null){
            val intent = Intent(this, DashboardActivity::class.java)

            intent.putExtra("UserID", user.uid)
            startActivity(intent)
        }
    }
}