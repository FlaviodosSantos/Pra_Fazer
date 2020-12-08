package com.example.prafazer;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "app.db";
    private static final int VERSAO_BANCO = 1;
    private Context context;
    private SQLiteDatabase dbInstancia = null;

    public DBHelper(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tarefas ( " +
                "id INTEGER PRIMARY KEY  AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "data TEXT, " +
                "hora TEXT, " +
                "tipo TEXT, " +
                "descricao TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tarefas");
        onCreate(db);
    }

    public void salvarTarefa(Tarefa tarefa) throws SQLException {
        abrirDB();
        dbInstancia.insert("tarefas", null, tarefa.getContentValues());
        fecharDB();
    }

    public ArrayList<Tarefa> getDBTarefas() {
        ArrayList<Tarefa> minhasTarefas = new ArrayList<Tarefa>();
        SQLiteDatabase meuBanco = getReadableDatabase();
        Cursor minhaConsulta = meuBanco.rawQuery("SELECT nome, data, hora, tipo, descricao FROM tarefas ORDER BY data", null);
        minhaConsulta.moveToFirst();
        while (! minhaConsulta.isAfterLast())
        {
            Tarefa atual = new Tarefa(minhaConsulta.getString(0), minhaConsulta.getString(1), minhaConsulta.getString(2), minhaConsulta.getString(3), minhaConsulta.getString(4));
            minhasTarefas.add(atual);
            minhaConsulta.moveToNext();
        }
        meuBanco.close();
        minhaConsulta.close();
        return minhasTarefas;
    }

    public void  abrirDB() throws SQLException {
        if (this.dbInstancia == null){
            this.dbInstancia = this.getWritableDatabase();
        }
    }

    public void  fecharDB() throws SQLException{
        if (this.dbInstancia != null){
            if (this.dbInstancia.isOpen())
                this.dbInstancia.close();
        }
    }
}
