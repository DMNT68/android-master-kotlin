package com.andres_sagadoc.androidmaster.TODO

import android.content.res.ColorStateList
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: TextView = view.findViewById(R.id.tvNameTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {
        tvTask.text = task.name

        when (task.category) {
            TaskCategory.Business -> cbTask.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    cbTask.context,
                    R.color.todo_business_category
                )
            )


            TaskCategory.Other -> cbTask.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    cbTask.context,
                    R.color.todo_other_category
                )
            )

            TaskCategory.Personal -> cbTask.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    cbTask.context,
                    R.color.todo_personal_category
                )
            )

            TaskCategory.Work -> cbTask.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    cbTask.context,
                    R.color.todo_work_category
                )
            )
        }
    }
}