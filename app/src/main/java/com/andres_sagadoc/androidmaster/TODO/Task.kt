package com.andres_sagadoc.androidmaster.TODO

data class Task (val name: String, val category:TaskCategory, var isSelected: Boolean = false){
}