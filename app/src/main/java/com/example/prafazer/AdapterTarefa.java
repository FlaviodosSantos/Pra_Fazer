package com.example.prafazer;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdapterTarefa extends ArrayAdapter<Tarefa> {
    private final Context context;
    private final ArrayList<Tarefa> listaTarefas;
    private Integer[] fotos = {R.drawable.ic_bombear, R.drawable.ic_ideia, R.drawable.ic_shuffle};

    public AdapterTarefa(Context context, ArrayList<Tarefa> listaTarefas) {
        super(context, R.layout.linha, listaTarefas);
        this.context = context;
        this.listaTarefas = listaTarefas;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater i = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = i.inflate(R.layout.linha, parent, false);

        ImageView iv_foto = linha.findViewById(R.id.iv_foto);
        TextView tv_data = linha.findViewById(R.id.tv_data);
        TextView tv_hora = linha.findViewById(R.id.tv_hora);
        TextView tv_nome = linha.findViewById(R.id.tv_nome);
        CheckBox cb_concluido = linha.findViewById(R.id.cb_concluida);

        Tarefa t = listaTarefas.get(position);

        tv_data.setText(t.getData());
        tv_hora.setText(t.getHora());
        tv_nome.setText(t.getNome());
        switch (t.getTipo()){
            case "Importante": iv_foto.setImageResource(fotos[0]); break;
            case "Tarefa": iv_foto.setImageResource(fotos[1]); break;
            case "Lembrar": iv_foto.setImageResource(fotos[2]); break;
        }

        return linha;
    }
}
