package com.mallcco.andy.laboratoriocalificado03

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeacherViewModel : ViewModel() {

    // LiveData para observar los cambios de la lista de profesores
    private val _teachers = MutableLiveData<List<Teacher>>()
    val teachers: LiveData<List<Teacher>> get() = _teachers

    // LiveData para manejar errores
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    // Función para obtener la lista de profesores desde la API
    fun fetchTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getTeachers()
                if (response.isSuccessful) {
                    _teachers.postValue(response.body()?.teachers ?: emptyList())
                } else {
                    _errorMessage.postValue("Error al obtener los datos: ${response.code()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Error de conexión: ${e.message}")
            }
        }
    }
}
