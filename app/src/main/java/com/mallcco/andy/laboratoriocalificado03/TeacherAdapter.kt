package com.mallcco.andy.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mallcco.andy.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private val context: Context,
    private var teachers: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            // Cargar nombre y apellido
            binding.teacherName.text = "${teacher.name} ${teacher.last_name}"

            // Cargar la imagen del profesor
            Glide.with(context)
                .load(teacher.imageUrl)
                .into(binding.teacherImage)

            // Configurar click normal (llamar por teléfono)
            binding.root.setOnClickListener {
                val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${teacher.phone}"))
                context.startActivity(phoneIntent)
            }

            // Configurar click largo (enviar correo)
            binding.root.setOnLongClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${teacher.email}"))
                context.startActivity(Intent.createChooser(emailIntent, "Enviar correo"))
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    override fun getItemCount(): Int = teachers.size

    fun updateList(newTeachers: List<Teacher>) {
        teachers = newTeachers
        notifyDataSetChanged()
    }
}
