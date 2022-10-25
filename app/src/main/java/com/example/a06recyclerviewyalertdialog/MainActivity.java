package com.example.a06recyclerviewyalertdialog;

import android.content.DialogInterface;
import android.os.Bundle;

import com.example.a06recyclerviewyalertdialog.adapters.TodosAdapter;
import com.example.a06recyclerviewyalertdialog.modelos.Todo;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a06recyclerviewyalertdialog.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Todo> todosList;

    private TodosAdapter adapter;

    //Encargado de indicar como se organizaran los elementos en el recicler
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        todosList = new ArrayList<>();

        //creaTodos();

        adapter = new TodosAdapter(MainActivity.this, todosList, R.layout.todo_model_view);
        layoutManager = new LinearLayoutManager(MainActivity.this);

        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTodo().show();
            }
        });
    }

    private AlertDialog createTodo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add new Todo");
        builder.setCancelable(false);
        //TIENE QUE CARGAR UN LAYOUT

        View alertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.todo_model_alert, null);
        TextView txtTitulo = alertView.findViewById(R.id.txtTituloTodoModelAlert);
        TextView txtContenido = alertView.findViewById(R.id.txtContenidoTodoModelAlert);
        builder.setView(alertView);

        //CREAR BOTONES
        builder.setNegativeButton("cancelar",null);
        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!txtTitulo.getText().toString().isEmpty() && !txtContenido.getText().toString().isEmpty()){
                    Todo todo = new Todo(txtTitulo.getText().toString(), txtContenido.getText().toString(), false);
                    todosList.add(todo);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Faltan Datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return builder.create();
    }

    private void creaTodos() {
        for (int i = 0; i < 1000; i++) {
            todosList.add(new Todo("Tarea " + i, "Contenido " + i, false));
        }
    }
}