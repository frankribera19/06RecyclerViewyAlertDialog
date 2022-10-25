package com.example.a06recyclerviewyalertdialog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a06recyclerviewyalertdialog.R;
import com.example.a06recyclerviewyalertdialog.modelos.Todo;

import java.util.ArrayList;


public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodoVH>{

    //Elementos para que funcione el Recycler:
    //El contexto es la activity ue contiene el RecyclerView
    private Context context;
    //Lo siguiente que se necesitan son los datos a mostrar
    private ArrayList<Todo> objects;
    //por ultimo se necesita la plantilla para los datos
    private int cardLayout;

    public TodosAdapter(Context context, ArrayList<Todo> objects, int cardLayout) {
        this.context = context;
        this.objects = objects;
        this.cardLayout = cardLayout;
    }

    /**
     * Se llama de forma automtica para que se creen nuevos elementos de la plantilla
     * @param parent
     * @param viewType
     * @return Un objeto CARD ya lISTO PARA ASIGNAR DATOS
     */
    @NonNull
    @Override
    public TodoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(context).inflate(cardLayout, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        todoView.setLayoutParams(layoutParams);
        return new TodoVH(todoView);
    }

    /**
     * Asignará los valores a los elementos de la vista del card
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TodoVH holder, int position) {
        Todo todo = objects.get(position);
        holder.lblTitulo.setText(todo.getTitulo());
        holder.lblContenido.setText(todo.getContenido());
        holder.lblFecha.setText(todo.getFecha().toString());

        if (todo.isCompletado()){
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        }

        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaUser("Estas seguro de cambiar el estado", todo).show();
            }
        });

        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarBorrado("Quieres borrar", todo).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * Restornar la cantidd de elemntos que hay que instanciar
     * @return
     */
    @Override
    public int getItemCount() {
        return objects.size();
    }

    private AlertDialog confirmarBorrado(String menaaje, Todo todo){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(menaaje);
        builder.setCancelable(false);

        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                objects.remove(todo);
                notifyDataSetChanged();
            }
        });

        return builder.create();

    }

    private AlertDialog confirmaUser(String mensaje, Todo todo){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(mensaje);
        builder.setCancelable(false);

        builder.setNegativeButton("NO", null);
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                todo.setCompletado(!todo.isCompletado());
                notifyDataSetChanged();
            }
        });

        return builder.create();
    }

    public class TodoVH extends RecyclerView.ViewHolder{
        TextView lblTitulo, lblContenido, lblFecha;
        ImageButton btnCompletado, btnBorrar;
        public TodoVH(@NonNull View itemView) {
            super(itemView);

            lblTitulo = itemView.findViewById(R.id.lblTituloTodoViewModel);
            lblContenido = itemView.findViewById(R.id.lblContenidoTodoViewModel);
            lblFecha = itemView.findViewById(R.id.lblFechaTodoViewModel);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoTodoViewModel);
            btnBorrar = itemView.findViewById(R.id.btnBorrarTodoViewModel);

        }
    }
}
