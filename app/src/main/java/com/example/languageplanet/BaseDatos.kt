package com.example.languageplanet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.languageplanet.databinding.ActivityBaseDatosBinding
import com.google.firebase.firestore.FirebaseFirestore

class BaseDatos : AppCompatActivity() {

    private lateinit var binding: ActivityBaseDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_datos)

        //Conexión base de datos Firestore
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        //BD ejercicios
        val alumnos = db.collection("alumnos")

        binding = ActivityBaseDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras.setOnClickListener{
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        binding.btnLista.setOnClickListener {

            //Base de datos Ejercicio1
            var datos = ""
            alumnos
                .get()
                .addOnSuccessListener { resultado ->
                    for(documento in resultado){
                        datos += "ALUMNO: ${documento.data.get("nombre")}\n" +
                                "Teléfono: ${documento.data.get("telefono")}\n" +
                                "Dirección: ${documento.data.get("direccion")}\n" +
                                "Resultados Exámenes Oficiales: ${documento.data.get("examenesOficiales")}\n" +
                                "Notas de la Academia (últimos 5 años): \n"+
                                "1er Año -> 1er Trim.= ${documento.data.get("prim1trim")} | " +
                                "2º Trim.= ${documento.data.get("seg1trim")} | " +
                                "3er Trim.= ${documento.data.get("ter1trim")}\n"+
                                "2º Año  -> 1er Trim.= ${documento.data.get("prim2trim")} | " +
                                "2º Trim.= ${documento.data.get("seg2trim")} | " +
                                "3er Trim.= ${documento.data.get("ter2trim")}\n"+
                                "3er Año -> 1er Trim.= ${documento.data.get("prim3trim")} | " +
                                "2º Trim.= ${documento.data.get("seg3trim")} | " +
                                "3er Trim.= ${documento.data.get("ter3trim")}\n"+
                                "4º Año  -> 1er Trim.= ${documento.data.get("prim4trim")} | " +
                                "2º Trim.= ${documento.data.get("seg4trim")} | " +
                                "3er Trim.= ${documento.data.get("ter4trim")}\n"+
                                "5º Año  -> 1er Trim.= ${documento.data.get("prim5trim")} | " +
                                "2º Trim.= ${documento.data.get("seg5trim")} | " +
                                "3er Trim.= ${documento.data.get("ter5trim")}\n"+
                                "Notas Exámenes de Práctica: ${documento.data.get("notasPracticas")}\n" +
                                "________________________________________\n\n"
                    }
                    binding.tvLista.text = datos
                }
                .addOnFailureListener { exception ->
                    binding.tvLista.text = "Fallo en la conexión a Internet"
                }
        }

    }
}