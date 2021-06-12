package com.example.agenda;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.agenda.database.AgendaOpenHelper;
import com.example.agenda.dominio.entidades.Pessoa;
import com.example.agenda.dominio.repositorio.ClienteRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstPersons;
    private  FloatingActionButton fab;
    private SQLiteDatabase conexao;
    private AgendaOpenHelper agendaOpenHelper;

    private PessoaAdapter pessoaAdapter;

    private ClienteRepositorio clienteRepositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        lstPersons = (RecyclerView)findViewById(R.id.lstDados);
        createConection();
        lstPersons.setHasFixedSize(true);
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        lstPersons.setLayoutManager(LinearLayoutManager);
        clienteRepositorio = new ClienteRepositorio(conexao);
        List<Pessoa> dados =  clienteRepositorio.buscarTotos();
        pessoaAdapter = new PessoaAdapter(dados);

        lstPersons.setAdapter(pessoaAdapter);

    }

    private void createConection(){
        try {
            agendaOpenHelper = new AgendaOpenHelper(this);
            conexao = agendaOpenHelper.getWritableDatabase();
            Context contexto = getApplicationContext();
            String texto = "Conexção criada com sucesso";
            int duracao = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(contexto, texto,duracao);
            toast.show();
        }catch (SQLException ex){
            AlertDialog.Builder dlg =  new AlertDialog.Builder(this);
            dlg.setTitle("ERRO");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    public void cadastrarPerson(View  view){
        Intent cadastro_pessoas = new Intent(ActMain.this, ActCadPerson.class);
        startActivityForResult(cadastro_pessoas, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Pessoa> dados = clienteRepositorio.buscarTotos();
        pessoaAdapter = new PessoaAdapter(dados);
        lstPersons.setAdapter(pessoaAdapter);
    }
}