package com.quispe.ismael.usolayoutsv4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quispe.ismael.usolayoutsv4.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val NAME_KEY = "NAME_KEY"
    private val PHONE_KEY = "PHONE_KEY"
    private val PRODUCTS_KEY = "PRODUCTS_KEY"
    private val ADDRESS_KEY = "LOCATION_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showInformation(intent.extras)
        observeButtons(intent.extras)
    }

    private fun showInformation(bundle: Bundle?) {
        if (bundle != null) {
            val name = bundle.getString(NAME_KEY)
            val phone = bundle.getString(PHONE_KEY)
            val product = bundle.getString(PRODUCTS_KEY)
            val address = bundle.getString(ADDRESS_KEY)

            Log.d("DetailsActivity", "Received data - Name: $name, Phone: $phone, Product: $product, Address: $address")

            binding.tvName.text = "Nombre completo: $name"
            binding.tvPhone.text = "Número telefónico: $phone"
            binding.tvProductos.text = "Producto: $product"
            binding.tvAddress.text = "Dirección: $address"
        } else {
            Log.d("DetailsActivity", "No data received")
        }
    }

    private fun observeButtons(bundle: Bundle?) {
        binding.imbMaps.setOnClickListener {
            goMaps(bundle)
        }

        binding.imbWsp.setOnClickListener {
            goWsp(bundle)
        }

        binding.imbDial.setOnClickListener {
            goDial(bundle)
        }
    }

    private fun goWsp(bundle: Bundle?) {
        val phone = "+51${bundle?.getString(PHONE_KEY)}"
        val message = "Hola te he agregado a mi lista de contactos"

        val intentWsp = Intent()
        intentWsp.action = Intent.ACTION_VIEW
        intentWsp.data = Uri.parse("https://wa.me/$phone?text=$message")

        startActivity(intentWsp)
    }

    private fun goDial(bundle: Bundle?) {
        val phone = bundle?.getString(PHONE_KEY)

        val intentDial = Intent()
        intentDial.action = Intent.ACTION_DIAL
        intentDial.data = Uri.parse("tel:$phone")

        startActivity(intentDial)
    }

    private fun goMaps(bundle: Bundle?) {
        val phone = bundle?.getString(PHONE_KEY)
        val message = "Hola te he agregado a mi lista de contactos"

        val intentSms = Intent()
        intentSms.action = Intent.ACTION_SENDTO
        intentSms.data = Uri.parse("smsto:$phone")
        intentSms.putExtra("sms_body", message)

        startActivity(intentSms)
    }
}
