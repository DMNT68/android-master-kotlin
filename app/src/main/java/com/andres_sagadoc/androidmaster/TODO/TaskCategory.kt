package com.andres_sagadoc.androidmaster.TODO

sealed class TaskCategory(var isSelected: Boolean = true) {
    object Business : TaskCategory()
    object Personal : TaskCategory()
    object Work : TaskCategory()
    object Other : TaskCategory()

}