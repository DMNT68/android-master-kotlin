package com.andres_sagadoc.androidmaster.TODO

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

// Contiene el layout de un elemento de la lista
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)

    fun render(taskCategory: TaskCategory) {
        tvCategoryName.text = "Ejemplo"
    }
}