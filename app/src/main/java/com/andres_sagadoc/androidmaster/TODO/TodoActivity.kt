package com.andres_sagadoc.androidmaster.TODO

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.TODO.TaskCategory.*

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Work,
        Other
    )

    private val tasks = listOf<Task>(
        Task("Personal", Personal, false),
        Task("Trabajo 8 a 5", Work, false),
        Task("Suscribirse al canal", Personal, false),
        Task("Vender sistema", Business, false),
        Task("Estudiar ingles", Personal, false),
        Task("Terminar el listado", Work, false),
        Task("Terminar Rachet and clank", Personal, false),
        Task("Reunirse con el cliente", Business, false),
        Task("Terminal Ralomtex", Work, false),
        Task("Entregar movilitix", Work, false),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initUI()
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
    }


    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTask)
    }
}