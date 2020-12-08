package com.example.prafazer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton fab;
    ListView listView;
    AdapterTarefa adapter;
    private DBHelper db;
    ArrayList<Tarefa> objetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        objetos = pegarDados();
        adapter = new AdapterTarefa(this, pegarDados());
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb = (CheckBox) view.findViewById(R.id.cb_concluida);
                boolean currentCheck = cb.isChecked();
                Tarefa t = (Tarefa) listView.getItemAtPosition(position);
                t.setConcluida(!currentCheck);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"teste",Toast.LENGTH_SHORT).show();
            }
        });

        fab = findViewById(R.id.fab);
        configBotoes();
    }

    private ArrayList<Tarefa> pegarDados() {
        return db.getDBTarefas();

    }

    private void configBotoes() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastroTarefa.class));
            }
        });

    }

    @Override
    protected void onRestart() {

        objetos.clear();
        objetos.addAll(pegarDados());

        adapter.notifyDataSetChanged();

        listView.invalidateViews();
        listView.refreshDrawableState();

        super.onRestart();
    }
}