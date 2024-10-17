package com.andres_sagadoc.androidmaster.TODO

sealed class TaskCategory {
    object Business : TaskCategory()
    object Personal : TaskCategory()
    object Work : TaskCategory()

}