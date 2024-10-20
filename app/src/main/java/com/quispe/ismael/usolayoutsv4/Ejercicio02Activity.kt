package com.quispe.ismael.usolayoutsv4

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quispe.ismael.usolayoutsv4.databinding.ActivityEjercicio01Binding
import com.quispe.ismael.usolayoutsv4.databinding.ActivityEjercicio02Binding
class Ejercicio02Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio02Binding
    private val NAME_KEY = "NAME_KEY"
    private val PHONE_KEY = "PHONE_KEY"
    private val PRODUCTS_KEY = "PRODUCTS_KEY"
    private val ADDRESS_KEY = "LOCATION_KEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio02)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)
        setContentView(binding.root)
        observeButtons()
    }
    private fun observeButtons() {
        binding.btnRegister.setOnClickListener {
            goDetailActivity()
        }
    }
    private fun goDetailActivity() {

        val name = binding.edtName.text.toString()
        val phone = binding.edtPhone.text.toString()
        val product = binding.edtProductos.text.toString()
        val address = binding.edtAddress.text.toString()

        val intent = Intent(this, DetailsActivity::class.java)
        Log.d("Ejercicio02Activity", "Sending data - Name: $name, Phone: $phone, Product: $product, Address: $address")

        intent.putExtra(NAME_KEY, name)
        intent.putExtra(PHONE_KEY, phone)
        intent.putExtra(PRODUCTS_KEY, product)
        intent.putExtra(ADDRESS_KEY, address)
        startActivity(intent)
    }
}