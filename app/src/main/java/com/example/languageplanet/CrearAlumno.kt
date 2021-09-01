package com.example.languageplanet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.languageplanet.databinding.ActivityCrearAlumnoBinding
import com.google.firebase.firestore.FirebaseFirestore

class CrearAlumno : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAlumnoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_alumno)

        //Conexión base de datos Firestore
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        //BD alumnos
        val alumnos = db.collection("alumnos")
        var crearAlumnoComprobacion = true

        binding = ActivityCrearAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAtras2.setOnClickListener{
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnCrear.setOnClickListener{
            if(binding.etNombre.text.isBlank()) Toast.makeText(this,"Error campo Nombre y Apellidos vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etTelefono.text.isBlank()) Toast.makeText(this,"Error campo Teléfono vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etDireccion.text.isBlank()) Toast.makeText(this,"Error campo Direccion vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etExamenesOficiales.text.isBlank()) Toast.makeText(this,"Error campo Exámenes Oficiales vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etNotasPracticas.text.isBlank()) Toast.makeText(this,"Error campo Notas Prácticas vacio", Toast.LENGTH_SHORT).show();

            else if(binding.etNombre.text.isNotBlank() && binding.etTelefono.text.isNotBlank() &&
                    binding.etDireccion.text.isNotBlank() && binding.etExamenesOficiales.text.isNotBlank()
                    && binding.etNotasPracticas.text.isNotBlank()){

                val dato = hashMapOf(
                        "nombre" to binding.etNombre.text.toString(),
                        "telefono" to binding.etTelefono.text.toString(),
                        "direccion" to binding.etDireccion.text.toString(),
                        "examenesOficiales" to binding.etExamenesOficiales.text.toString(),
                        "notasPracticas" to binding.etNotasPracticas.text.toString(),
                        "prim1trim" to binding.etprimerprimer.text.toString(),
                        "seg1trim" to binding.etsegundoprimer.text.toString(),
                        "ter1trim" to binding.ettercerprimer.text.toString(),
                        "prim2trim" to binding.etprimersegundo.text.toString(),
                        "seg2trim" to binding.etsegundosegundo.text.toString(),
                        "ter2trim" to binding.ettercersegundo.text.toString(),
                        "prim3trim" to binding.etprimertercer.text.toString(),
                        "seg3trim" to binding.etsegundotercer.text.toString(),
                        "ter3trim" to binding.ettercertercer.text.toString(),
                        "prim4trim" to binding.etprimercuarto.text.toString(),
                        "seg4trim" to binding.etsegundocuarto.text.toString(),
                        "ter4trim" to binding.ettercercuarto.text.toString(),
                        "prim5trim" to binding.etprimerquinto.text.toString(),
                        "seg5trim" to binding.etsegundoquinto.text.toString(),
                        "ter5trim" to binding.ettercerquinto.text.toString()
                )

                var nombreConsulta: String = binding.etNombre.text.toString()
                //Consultas para encontrar los alumnos con dichos parametros
                alumnos.whereEqualTo("nombre", nombreConsulta)
                    .get()
                    .addOnSuccessListener { result ->
                        crearAlumnoComprobacion = false
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this,"Error de conexión a la base de datos", Toast.LENGTH_SHORT).show()
                    }

                //Variable de tiempo
                val handler = Handler()

                //Comprobacion de nombre y apellidos del alumno que vamos a crear y si existe no crearlo
                handler.postDelayed({
                    if(crearAlumnoComprobacion){
                        alumnos
                            .document()
                            .set(dato)
                            .addOnSuccessListener { _ ->
                                Toast.makeText(this,"Alumno creado correctamente", Toast.LENGTH_LONG).show()
                            }
                            .addOnFailureListener { _ ->
                                Toast.makeText(this,"Error de conexión a la base de datos", Toast.LENGTH_SHORT).show()
                            }
                    }
                    else{
                        Toast.makeText(this,"Error Alumno con el mismo Nombre y Apellidos en la Base de Datos", Toast.LENGTH_SHORT).show()
                    }
                }, 1000)

            }

        }

    }
}