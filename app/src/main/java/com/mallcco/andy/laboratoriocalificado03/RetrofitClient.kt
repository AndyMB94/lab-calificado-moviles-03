package com.mallcco.andy.laboratoriocalificado03

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Define la URL base de la API
    private const val BASE_URL = "https://private-effe28-tecsup1.apiary-mock.com/"

    // Creación de la instancia de Retrofit utilizando un objeto Singleton
    // La instancia de Retrofit se crea solo una vez debido a 'lazy'
    val apiService: TeacherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Base URL de la API
            .addConverterFactory(GsonConverterFactory.create()) // Converter para convertir JSON a objetos Kotlin
            .build()
            .create(TeacherApi::class.java) // Crear la interfaz que define las llamadas de la API
    }
}
