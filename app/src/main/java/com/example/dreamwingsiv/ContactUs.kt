package com.example.dreamwingsiv

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged

class ContactUs : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextMessage = findViewById(R.id.editTextMessage)

        // Enable or disable the "Send Message" button based on form validation
        editTextName.doAfterTextChanged { validateForm() }
        editTextEmail.doAfterTextChanged { validateForm() }
        editTextMessage.doAfterTextChanged { validateForm() }
    }

    fun sendMessage(view: View) {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val message = editTextMessage.text.toString()

        val subject = "Contact Form Submission"
        val emailBody = "Name: $name\nEmail: $email\n\n$message"

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:gakungashelmith4smile@gmail.com")
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, emailBody)
        }

        val chooserIntent = Intent.createChooser(intent, "Send Email")

        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }

        // Clear the form fields after input
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextMessage.text.clear()
    }

    private fun validateForm() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val message = editTextMessage.text.toString()

        val isFormValid = name.isNotBlank() && email.isNotBlank() && message.isNotBlank()
        findViewById<Button>(R.id.buttonSendMessage)?.isEnabled = isFormValid
    }
}