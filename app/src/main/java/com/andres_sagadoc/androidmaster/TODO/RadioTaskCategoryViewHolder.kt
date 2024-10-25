package com.andres_sagadoc.androidmaster.TODO

import android.content.res.ColorStateList
import android.view.View
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

class RadioTaskCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val radio = view.findViewById<RadioButton>(R.id.radio)

    fun render(
        taskCategory: TaskCategory,
        isSelected: Boolean,
        onItemCategorySelected: (Int) -> Unit
    ) {

        radio.isChecked = isSelected

        radio.setOnClickListener {
            onItemCategorySelected(adapterPosition)  // Pasar la posición seleccionada al adaptador
        }

        when (taskCategory) {
            TaskCategory.Business -> {
                radio.text = ContextCompat.getString(radio.context, R.string.todo_business)
                radio.buttonTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        radio.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Other -> {
                radio.text = ContextCompat.getString(radio.context, R.string.todo_other)
                radio.buttonTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        radio.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                radio.text = ContextCompat.getString(radio.context, R.string.todo_personal)
                radio.buttonTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        radio.context,
                        R.color.todo_personal_category
                    )
                )
            }

            TaskCategory.Work -> {
                radio.text = ContextCompat.getString(radio.context, R.string.todo_work)
                radio.buttonTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        radio.context,
                        R.color.todo_work_category
                    )
                )
            }
        }
    }
}