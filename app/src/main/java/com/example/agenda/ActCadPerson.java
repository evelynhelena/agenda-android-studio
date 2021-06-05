package com.example.agenda;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.database.AgendaOpenHelper;
import com.example.agenda.dominio.entidades.Pessoa;
import com.example.agenda.dominio.repositorio.ClienteRepositorio;
import com.google.android.material.snackbar.Snackbar;

public class ActCadPerson extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtPhone;
    private ClienteRepositorio cliente;

    private SQLiteDatabase conection;
    private AgendaOpenHelper agendaOpenHelper;

    private Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_cad_person);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome  = (EditText)findViewById(R.id.idName);
        edtEmail = (EditText)findViewById(R.id.idEmail);
        edtPhone = (EditText)findViewById(R.id.idPhone);
        createConection();
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
            cliente = new ClienteRepositorio(conection);

        }catch (SQLException ex){
            AlertDialog.Builder dlg =  new AlertDialog.Builder(this);
            dlg.setTitle("ERRO");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_person,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void confirm(){
        pessoa = new Pessoa();
        if(!validaCampos()){
            try{
                cliente.insert(pessoa);
                finish();
            }catch (SQLException ex){
                AlertDialog.Builder dlg =  new AlertDialog.Builder(this);
                dlg.setTitle("ERRO");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("OK",null);
                dlg.show();
            }
        }
    }
    private boolean validaCampos(){

        boolean res = false;

        String nome  = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();

        pessoa.nome  = nome;
        pessoa.email = email;
        pessoa.phone = phone;


        if(res = isCampoVazio(nome)){//res recebe o retorno da validação
            edtPhone.requestFocus();
        }else if(res = !isEmailValido(email)){
            edtEmail.requestFocus();
        }else if(res = isCampoVazio(phone)){
            edtPhone.requestFocus();
        }

        if(res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage("Há campos inválidos ou em branco");
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        return res;
    }

    private boolean isCampoVazio(String valor){
        //Valida se ta vazio e retira o espaço e valida se esta vazio
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private boolean isEmailValido(String email){
        //Valida se é um email valido
        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                confirm();
//                Toast.makeText(this, "Botão OK Selecionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
//                Toast.makeText(this, "Botão Cancelar Selecionado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}