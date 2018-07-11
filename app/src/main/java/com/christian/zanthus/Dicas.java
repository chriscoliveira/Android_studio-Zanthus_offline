package com.christian.zanthus;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Dicas extends Activity {
    protected SQLiteDatabase bancoDados = null;
    public static String NOME_BANCO = "Zanthus2017";
    public static String NOME_TABELA = "bancoZanthus";
    public static String CODIGO = "codigo";
    public static String DESCRICAO = "descricao";


    ListView MostraDados;
    SimpleCursorAdapter adapterLista;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_dicas);


    }


}
