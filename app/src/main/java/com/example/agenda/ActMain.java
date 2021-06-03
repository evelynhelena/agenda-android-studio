package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class ActMain extends AppCompatActivity {

    private RecyclerView lstPersons;
    private  FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        lstPersons = (RecyclerView)findViewById(R.id.lstDados);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarPerson();
            }
        });*/
    }

    public void cadastrarPerson(View  view){
        Intent cadastro_pessoas = new Intent(ActMain.this, ActCadPerson.class);
        startActivity(cadastro_pessoas);
    }

}