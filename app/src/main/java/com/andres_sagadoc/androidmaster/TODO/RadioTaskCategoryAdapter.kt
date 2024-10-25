package com.andres_sagadoc.androidmaster.TODO

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

class RadioTaskCategoryAdapter(private val categories: List<TaskCategory>) :
    RecyclerView.Adapter<RadioTaskCategoryViewHolder>() {

    private var selectedPosition = -1  // Posición del RadioButton seleccionado

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioTaskCategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio_button, parent, false)
        return RadioTaskCategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: RadioTaskCategoryViewHolder, position: Int) {
        val option = categories[position]
        holder.render(option, selectedPosition == position) {
            selectedPosition = it  // Actualiza la posición seleccionada
            notifyDataSetChanged()  // Refresca el RecyclerView para reflejar el cambio
        }
    }

    // Función para obtener la opción seleccionada
    fun getSelectedOption(): TaskCategory? {
        return if (selectedPosition != -1) categories[selectedPosition] else null
    }

}