package com.example.dreamwingsiv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dreamwingsiv.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.signbtn.setOnClickListener {
            val email = binding.emmy.text.toString()
            val password = binding.pass.text.toString()
            if (checkAllFields()){
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val jake = Intent(this@Login,Home::class.java)
                        startActivity(jake)
                        finish()
                    }else{
                        Log.e("error",it.exception.toString())
                    }
                }
            }
        }
        binding.create.setOnClickListener {
            val jack = Intent(this@Login,SignIn::class.java)
            startActivity(jack)
            finish()
        }
    }
    private fun checkAllFields(): Boolean{
        val email = binding.emmy.text.toString()
        if(binding.emmy.text.toString()==""){
            binding.emaillayout.error = "Kindly Input Your Email"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emaillayout.error = "Kindly Input Using the correct Format"
            return false
        }
        if (binding.pass.text.toString() ==""){
            binding.passlayout.error = "Kindly Enter You Password"
            binding.passlayout.errorIconDrawable = null
            return false
        }
        if(binding.pass.length() <=8){
            binding.passlayout.error = "Password is too short"
            binding.passlayout.errorIconDrawable = null
        }
        return true
    }
}