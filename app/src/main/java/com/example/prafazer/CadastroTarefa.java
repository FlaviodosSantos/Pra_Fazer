package com.example.prafazer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CadastroTarefa extends AppCompatActivity {
    TextInputEditText nome;
    CalendarView data;
    String dataselecionada;
    EditText hora;
    TimePickerDialog picker;
    Spinner tipo;
    EditText descricao;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);
        setTitle(getString(R.string.cadastro));

        db = new DBHelper(this);

        nome = findViewById(R.id.nome);
        data = findViewById(R.id.data);
        descricao = findViewById(R.id.descricao);

        tipo = findViewById(R.id.tipo);
        popularTipo();

        hora = findViewById(R.id.hora);
        hora.setInputType(InputType.TYPE_NULL);
        configBotoes();

    }

    private void popularTipo() {
        ArrayAdapter<String> tipos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.spinner_tipo));
        tipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(tipos);
    }

    private void configBotoes() {
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(CadastroTarefa.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hora.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minutes, true);
                picker.show();

            }
        });

        data.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dataselecionada = dayOfMonth+"/"+(month+1)+"/"+year;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_novatarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_salvar:
                Toast.makeText(getApplicationContext(),  "Tarefa Cadastrada", Toast.LENGTH_LONG).show();
                Tarefa tarefa = new Tarefa(nome.getText().toString(), dataselecionada, hora.getText().toString(),tipo.getSelectedItem().toString(),descricao.getText().toString());
                try {
                    db.salvarTarefa(tarefa);
                    Toast.makeText(getApplicationContext(), "Tarefa Cadastrada.", Toast.LENGTH_LONG).show();
                    finish();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Falha no Cadastro.", Toast.LENGTH_LONG).show();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}