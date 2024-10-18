package com.andres_sagadoc.androidmaster.TODO

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andres_sagadoc.androidmaster.R
import com.andres_sagadoc.androidmaster.TODO.TaskCategory.Business
import com.andres_sagadoc.androidmaster.TODO.TaskCategory.Other
import com.andres_sagadoc.androidmaster.TODO.TaskCategory.Personal
import com.andres_sagadoc.androidmaster.TODO.TaskCategory.Work
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Work,
        Other
    )

    private var tasks = mutableListOf<Task>(
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
    private lateinit var fabAddTask: FloatingActionButton


    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }


    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()){
                val selectedId = rgCategories.checkedRadioButtonId
                val seletedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currenteCategory: TaskCategory = when(seletedRadioButton.text){
                    getString(R.string.todo_business) -> Business
                    getString(R.string.todo_personal) -> Personal
                    getString(R.string.todo_work) -> Work
                    else -> Other
                }
                tasks.add(0,Task(currentTask, currenteCategory, false))
                tasksAdapter.notifyItemInserted(0)
                etTask.text.clear()
                dialog.hide()
            }
        }
        dialog.show()
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
        fabAddTask = findViewById(R.id.fabAddTask)

    }
}