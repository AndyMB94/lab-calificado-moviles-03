package com.mallcco.andy.laboratoriocalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mallcco.andy.laboratoriocalificado03.databinding.ActivityEjercicio01Binding

class Ejercicio01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private val adapter by lazy { TeacherAdapter(this, emptyList()) }

    private val viewModel: TeacherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
        binding.rvTeachers.adapter = adapter

        // Observamos los cambios en la lista de profesores
        viewModel.teachers.observe(this, Observer { teachers ->
            teachers?.let { adapter.updateList(it) }
        })

        // Observamos los errores
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        // Llamar a la función para obtener los datos
        viewModel.fetchTeachers()
    }
}
