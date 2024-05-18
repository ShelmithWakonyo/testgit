package com.example.dreamwingsiv

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import com.paypal.android.sdk.payments.PaymentConfirmation
import org.json.JSONException
import java.math.BigDecimal

class PaypalPage : AppCompatActivity() {

    private val PAYPAL_REQUEST_CODE = 123;

    // Configure your PayPal environment and credentials
    private val config = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId("ASEJAaECNCCc4ZivoJtGe-LrJ4k5kHvIboz4YDuIUdCH5rzDdkcTPti1WS8vbOn0LeASRUbvgaXusQoA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paypal_page)

        val payment = PayPalPayment(
            BigDecimal("10.00"), "USD", "Payment Description",
            PayPalPayment.PAYMENT_INTENT_SALE)
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val confirm = data?.getParcelableExtra<PaymentConfirmation>(PaymentActivity.EXTRA_RESULT_CONFIRMATION)
                if (confirm != null) {
                    try {
                        val paymentDetails = confirm.toJSONObject().toString(4)
                        // Process payment success
                        processPaymentSuccess(paymentDetails)
                    } catch (e: JSONException) {
                        // Handle JSON parsing error
                        handleJsonParsingError(e)
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Payment cancelled by the user
                handlePaymentCancelled()
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // Invalid payment configuration
                handleInvalidPaymentConfiguration()
            }
        }
    }

    private fun processPaymentSuccess(paymentDetails: String) {
        // Handle successful payment
        // Display payment details or perform other actions
        println("Payment successful: $paymentDetails")
    }

    private fun handleJsonParsingError(exception: JSONException) {
        // Handle JSON parsing error
        println("JSON parsing error: ${exception.message}")
    }

    private fun handlePaymentCancelled() {
        // Handle payment cancellation
        println("Payment cancelled by the user")
    }

    private fun handleInvalidPaymentConfiguration() {
        // Handle invalid payment configuration
        println("Invalid payment configuration")
    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }
}