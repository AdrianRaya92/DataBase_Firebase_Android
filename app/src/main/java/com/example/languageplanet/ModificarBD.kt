package com.example.languageplanet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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

                            //Mostrar en modificar los datos del alumno consultado
                            binding.etNombre.setText("${documento.data.get("nombre")}")
                            binding.etTelefono.setText("${documento.data.get("telefono")}")
                            binding.etDireccion.setText("${documento.data.get("direccion")}")
                            binding.etExamenesOficiales.setText("${documento.data.get("examenesOficiales")}")
                            binding.etNotasPracticas.setText("${documento.data.get("notasPracticas")}")
                            binding.etprimerprimer.setText("${documento.data.get("prim1trim")}")
                            binding.etsegundoprimer.setText("${documento.data.get("seg1trim")}")
                            binding.ettercerprimer.setText("${documento.data.get("ter1trim")}")
                            binding.etprimersegundo.setText("${documento.data.get("prim2trim")}")
                            binding.etsegundosegundo.setText("${documento.data.get("seg2trim")}")
                            binding.ettercersegundo.setText("${documento.data.get("ter2trim")}")
                            binding.etprimertercer.setText("${documento.data.get("prim3trim")}")
                            binding.etsegundotercer.setText("${documento.data.get("seg3trim")}")
                            binding.ettercertercer.setText("${documento.data.get("ter3trim")}")
                            binding.etprimercuarto.setText("${documento.data.get("prim4trim")}")
                            binding.etsegundocuarto.setText("${documento.data.get("seg4trim")}")
                            binding.ettercercuarto.setText("${documento.data.get("ter4trim")}")
                            binding.etprimerquinto.setText("${documento.data.get("prim5trim")}")
                            binding.etsegundoquinto.setText("${documento.data.get("seg5trim")}")
                            binding.ettercerquinto.setText("${documento.data.get("ter5trim")}")
                        }
                        if (datos.equals("")){
                            binding.tvConsulta.text = "No hay alumnos con ese nombre en la base de datos. " +
                                    "Comprobar que se ha escrito correctamente el nombre y los espacios entre nombre y" +
                                    " apellido."
                        }
                        else {
                            binding.tvConsulta.text = datos
                            binding.etNombreConsulta.setText("")

                        }
                    }
                    .addOnFailureListener { exception ->
                        binding.tvConsulta.text = "Fallo en la conexión a Internet"
                    }
            }
        }

        //Modificación de alumno
        binding.btnModificar.setOnClickListener{
            if(binding.etNombre.text.isBlank()) Toast.makeText(this,"Error campo Nombre y Apellidos vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etTelefono.text.isBlank()) Toast.makeText(this,"Error campo Teléfono vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etDireccion.text.isBlank()) Toast.makeText(this,"Error campo Direccion vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etExamenesOficiales.text.isBlank()) Toast.makeText(this,"Error campo Exámenes Oficiales vacio", Toast.LENGTH_SHORT).show();
            else if(binding.etNotasPracticas.text.isBlank()) Toast.makeText(this,"Error campo Notas Prácticas vacio", Toast.LENGTH_SHORT).show();

            else if(binding.etNombre.text.isNotBlank() && binding.etTelefono.text.isNotBlank() &&
                binding.etDireccion.text.isNotBlank() && binding.etExamenesOficiales.text.isNotBlank()
                && binding.etNotasPracticas.text.isNotBlank()){

                //Variable de tiempo
                val handler = Handler()

                handler.postDelayed({
                    //Consulta del alumno en BD
                    var datosModificar= ""
                    //Consultas para encontrar los alumnos con dichos parametros
                    alumnos.whereEqualTo("nombre", binding.etNombre.text.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            //Comprobación para ver si el alumno esta en la base de datos
                            for (document in result) {
                                datosModificar += "${document.id}"
                            }
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this,"Error de conexión a la base de datos", Toast.LENGTH_SHORT).show()
                        }


                    //Datos para registrar al alumno
                    var dato = hashMapOf(
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

                    //Comprobacion de nombre y apellidos del alumno que vamos a crear y si existe no crearlo
                    handler.postDelayed({
                        if(datosModificar.equals("")){
                            Toast.makeText(this,"Alumno no encontrado", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            alumnos
                                .document(datosModificar)
                                .set(dato)
                                .addOnSuccessListener { _ ->
                                    Toast.makeText(this, "Alumno Modificado correctamente", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { _ ->
                                    Toast.makeText(this, "Error de conexión a la base de datos", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }, 500)
                }, 500)

            }


        }

        //Borrar alumno
        binding.btnBorrar.setOnClickListener{
            if(binding.etNombreBorrar.text.isBlank()) Toast.makeText(this,"Error campo Nombre y Apellidos vacio", Toast.LENGTH_SHORT).show();
            else {

                //Comprobar que el alumno está en la BD
                var datosBorrar= ""
                alumnos.whereEqualTo("nombre", binding.etNombreBorrar.text.toString())
                    .get()
                    .addOnSuccessListener { result ->
                        //Comprobación para ver si el alumno esta en la base de datos
                        for (document in result) {
                            datosBorrar += "${document.id}"
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this,"Error de conexión a la base de datos", Toast.LENGTH_SHORT).show()
                    }

                //Variable de tiempo
                val handler = Handler()

                handler.postDelayed({

                    //Comprobar que se encuentra la id del alumno que se quiere borrar
                    if(datosBorrar.equals("")){
                        binding.tvBorrar.text = "Alumno no encontrado"
                    }
                    //Borrar al alumno de la BD
                    else {
                        alumnos
                            .document(datosBorrar)
                            .delete()
                            .addOnSuccessListener { result ->

                                binding.tvBorrar.text = "Alumno Borrado Correctamente"
                                binding.etNombreBorrar.setText("")
                            }
                            .addOnFailureListener { exception ->
                                binding.tvConsulta.text = "Fallo en la conexión a Internet"
                            }
                    }
                }, 500)


            }
        }

    }
}