package com.andres_sagadoc.androidmaster.TODO

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R

// Contiene el layout de un elemento de la lista
class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemCategorySelected: (Int) -> Unit) {

        val color = if(taskCategory.isSelected){
            R.color.todo_background_card
        }
        else{
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context, color))

        itemView.setOnClickListener { onItemCategorySelected(layoutPosition) }


        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.todo_business)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_business_category))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.todo_other)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_other_category))

            }
            TaskCategory.Personal -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.todo_personal)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_personal_category))

            }
            TaskCategory.Work -> {
                tvCategoryName.text = ContextCompat.getString(tvCategoryName.context, R.string.todo_work)
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.todo_work_category))

            }
        }
    }
}