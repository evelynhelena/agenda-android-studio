package com.example.agenda.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agenda.dominio.entidades.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    private SQLiteDatabase conection;
    public ClienteRepositorio(SQLiteDatabase conection){
        this.conection = conection;
    }

    public void insert(Pessoa pessoa){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",pessoa.nome);
        contentValues.put("EMAIL",pessoa.email);
        contentValues.put("PHONE",pessoa.phone);

        conection.insertOrThrow("CLIENTE", null, contentValues);
    }

    public void delete(int id){
        String[] params = new String[1];
        params[0] = String.valueOf(id);

        conection.delete("CLIENTE","CODIGO = ?" , params);
    }

    public void update(Pessoa pessoa){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",pessoa.nome);
        contentValues.put("EMAIL",pessoa.email);
        contentValues.put("PHONE",pessoa.phone);

        String[] params = new String[1];
        params[0] = String.valueOf(pessoa.codigo);

        conection.update("CLIENTE", contentValues,"CODIGO = ?" , params);
    }

    public List<Pessoa> buscarTotos(){

        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO,NOME,EMAIL,PHONE ");
        sql.append("FROM CLIENTE");
        Cursor resultado = conection.rawQuery(sql.toString(),null);
        if(resultado.getCount() > 0){
            resultado.moveToFirst();
            do {
                Pessoa ps = new Pessoa();

                ps.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                ps.nome   = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
                ps.email  = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
                ps.phone  = resultado.getString(resultado.getColumnIndexOrThrow("PHONE"));
                pessoas.add(ps);
            }while (resultado.moveToNext());
        }

        return pessoas;
    }

    public Pessoa buscarCliente(int id){
        Pessoa pessoa = new Pessoa();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO,NOME,EMAIL,PHONE");
        sql.append("FROM CLIENTE");
        sql.append("WHERE CODIGO = ?");

        String[] params = new String[1];
        params[0] = String.valueOf(id);

        Cursor resultado = conection.rawQuery(sql.toString(),params);

        if(resultado.getCount() > 0){
            resultado.moveToFirst();
            pessoa.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
            pessoa.nome   = resultado.getString(resultado.getColumnIndexOrThrow("NOME"));
            pessoa.email  = resultado.getString(resultado.getColumnIndexOrThrow("EMAIL"));
            pessoa.phone  = resultado.getString(resultado.getColumnIndexOrThrow("PHONE"));
            return pessoa;
        }

        return null;
    }
}
