package com.example.agenda;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.agenda.database.AgendaOpenHelper;
import com.example.agenda.dominio.entidades.Pessoa;
import com.example.agenda.dominio.repositorio.ClienteRepositorio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstPersons;
    private  FloatingActionButton fab;
    private SQLiteDatabase conection;
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

        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        lstPersons.setLayoutManager(LinearLayoutManager);
        clienteRepositorio = new ClienteRepositorio(conection);
        List<Pessoa> dados =  clienteRepositorio.buscarTotos();
        pessoaAdapter = new PessoaAdapter(dados);

        lstPersons.setAdapter(pessoaAdapter);

    }

    private void createConection(){
        try {
            agendaOpenHelper = new AgendaOpenHelper(this);
            conection = agendaOpenHelper.getWritableDatabase();
            AlertDialog.Builder dlg =  new AlertDialog.Builder(this);
            dlg.setTitle("sucesso");
            dlg.setMessage("deu certo");
            dlg.setNeutralButton("OK",null);
            dlg.show();
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
        startActivity(cadastro_pessoas);
    }

}