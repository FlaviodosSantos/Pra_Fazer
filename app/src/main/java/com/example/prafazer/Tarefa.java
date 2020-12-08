package com.example.prafazer;

import android.content.ContentValues;

import java.sql.Time;
import java.util.Date;

public class Tarefa {

    private Integer id;
    private String nome;
    private String data;
    private String hora;
    private String tipo;
    private String descricao;
    private Boolean concluida;

    public Tarefa(String nome, String data, String hora, String tipo, String descricao) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put("nome", this.nome);
        cv.put("data", String.valueOf(this.data));
        cv.put("hora", String.valueOf(this.hora));
        cv.put("tipo", this.tipo);
        cv.put("descricao", this.descricao);
        return cv;
    }
}
