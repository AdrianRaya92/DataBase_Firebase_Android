package com.example.languageplanet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languageplanet.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBD.setOnClickListener{
            val intent: Intent = Intent(this,BaseDatos::class.java)
            startActivity(intent)
        }

        binding.btnBDIntroducir.setOnClickListener{
            val intent: Intent = Intent(this,CrearAlumno::class.java)
            startActivity(intent)
        }

        binding.btnBDModificar.setOnClickListener{
            val intent: Intent = Intent(this,ModificarBD::class.java)
            startActivity(intent)
        }
    }
}