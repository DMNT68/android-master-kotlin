package com.andres_sagadoc.androidmaster.TODO

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

// Permite pintar las listas y mostrarlas en pantalla
class CategoriesAdapter(private val categories: List<TaskCategory>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {

    // Crear una vista para cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    // Hace que el listado tenga un tamaño
    override fun getItemCount(): Int = categories.size

    // Renderiza cada uno de los elementos en la lista
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position])
    }
}