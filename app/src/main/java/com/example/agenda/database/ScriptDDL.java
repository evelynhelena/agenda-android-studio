package com.example.agenda.database;

public class ScriptDDL {

    public static String getCreacteTableCliente(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS CLIENTE(");
        sql.append("CODIGO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("NOME VARCHAR(250) NOT NULL DEFAULT (''),");
        sql.append("EMAIL VARCHAR(200) NOT NULL DEFAULT (''),");
        sql.append("PHONE VARCHAR(20) NOT NULL DEFAULT ('') )");
        return sql.toString();
    }

}
