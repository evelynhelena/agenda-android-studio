package com.example.agenda.dominio.entidades;

import java.io.Serializable;

public class Pessoa implements Serializable {
    public int codigo;
    public String nome;
    public String email;
    public String phone;

    public Pessoa(){
        codigo = 0;
    }
}
