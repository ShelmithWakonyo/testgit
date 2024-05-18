package com.example.dreamwingsiv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dreamwingsiv.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignIn : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.signup.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (checkAllFields()){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val Rea = Intent(this@SignIn,Home::class.java)
                        startActivity(Rea)
                        finish()
                    }else{
                        Log.e("error",it.exception.toString())
                    }
                }
            }
        }

    }
    private fun checkAllFields():Boolean{
        val email = binding.email.text.toString()
        if (binding.email.text.toString() ==""){
            binding.elayout.error = "Kindly Input your Email"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.elayout.error = "Kindly Input the right format"
            return false
        }
        if (binding.password.text.toString() !=binding.confirmpassword.text.toString()){
            binding.passwordlayout.error = "Passwords don't match kindly try again"
        }
        return true
    }
}