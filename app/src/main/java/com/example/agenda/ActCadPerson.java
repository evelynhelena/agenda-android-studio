package com.example.agenda;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActCadPerson extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cad_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome  = (EditText)findViewById(R.id.idName);
        edtEmail = (EditText)findViewById(R.id.idEmail);
        edtPhone = (EditText)findViewById(R.id.idPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_person,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
                Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}