package com.example.languageplanet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.languageplanet.databinding.ActivityBaseDatosBinding
import com.example.languageplanet.databinding.ActivityModificarBdBinding
import com.google.firebase.firestore.FirebaseFirestore


class ModificarBD : AppCompatActivity() {

    private lateinit var binding: ActivityModificarBdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_bd)

        //Conexión base de datos Firestore
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        //BD alumno
        val alumnos = db.collection("alumnos")

        binding = ActivityModificarBdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras4.setOnClickListener{
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        //Consulta del alumno
        binding.btnConsultar.setOnClickListener {

            var datos = ""

            if(binding.etNombreConsulta.text.isBlank()) Toast.makeText(this,"Error campo Nombre y Apellidos vacio", Toast.LENGTH_SHORT).show();
            else {

                var nombreConsulta: String = binding.etNombreConsulta.text.toString()
                //Consultas para encontrar los alumnos con dichos parametros
                alumnos.whereEqualTo("nombre", nombreConsulta)
                    .get()
                    .addOnSuccessListener { result ->
                        //Bucle para encontrar todos los alumnos que tengan los parámetros consultados
                        for (documento in result) {
                            datos += "ALUMNO: ${documento.data.get("nombre")}\n" +
                                    "Teléfono: ${documento.data.get("telefono")}\n" +
                                    "Dirección: ${documento.data.get("direccion")}\n" +
                                    "Resultados Exámenes Oficiales: ${documento.data.get("examenesOficiales")}\n" +
                                    "Notas de la Academia (últimos 5 años): \n" +
                                    "1er Año -> 1er Trim.= ${documento.data.get("prim1trim")} | " +
                                    "2º Trim.= ${documento.data.get("seg1trim")} | " +
                                    "3er Trim.= ${documento.data.get("ter1trim")}\n" +
                                    "2º Año  -> 1er Trim.= ${documento.data.get("prim2trim")} | " +
                                    "2º Trim.= ${documento.data.get("seg2trim")} | " +
                                    "3er Trim.= ${documento.data.get("ter2trim")}\n" +
                                    "3er Año -> 1er Trim.= ${documento.data.get("prim3trim")} | " +
                                    "2º Trim.= ${documento.data.get("seg3trim")} | " +
                                    "3er Trim.= ${documento.data.get("ter3trim")}\n" +
                                    "4º Año  -> 1er Trim.= ${documento.data.get("prim4trim")} | " +
                                    "2º Trim.= ${documento.data.get("seg4trim")} | " +
                                    "3er Trim.= ${documento.data.get("ter4trim")}\n" +
                                    "5º Año  -> 1er Trim.= ${documento.data.get("prim5trim")} | " +
                                    "2º Trim.= ${documento.data.get("seg5trim")} | " +
                                    "3er Trim.= ${documento.data.get("ter5trim")}\n" +
                                    "Notas Exámenes de Práctica: ${documento.data.get("notasPracticas")}\n" +
                                    "________________________________________\n\n"
                        }
                        if (datos.equals("")){
                            binding.tvConsulta.text = "No hay alumnos con ese nombre en la base de datos. " +
                                    "Comprobar que se ha escrito correctamente el nombre y los espacios entre nombre y" +
                                    " apellido."
                        }
                        else {
                            binding.tvConsulta.text = datos
                        }
                    }
                    .addOnFailureListener { exception ->
                        binding.tvConsulta.text = "Fallo en la conexión a Internet"
                    }
            }
        }

        //Modificación de alumno


        //Borrar alumno

    }
}