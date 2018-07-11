/*
*
* 11072018 mudado o caminhos dos managers
*
*
 */
package com.christian.zanthus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Principal extends Activity {
    protected SQLiteDatabase bancoDados = null;
    public static String NOME_BANCO = "Zanthus2017";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btImportar, btFuncoes, btFalhas, btManager,btDicas;

        btFalhas = (Button) findViewById(R.id.btFalhas);
        btFuncoes = (Button) findViewById(R.id.bt_funcoes);
        btManager = (Button) findViewById(R.id.buttonmanager);
        btDicas = (Button) findViewById(R.id.bt_dicas);


        btFalhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, PesquisaFalhas.class);
                startActivity(intent);
            }
        });
        btFuncoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Funcoes_PDV.class);
                startActivity(intent);
            }
        });
        btManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Manager.class);
                startActivity(intent);
            }
        });
        btDicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this,Dicas.class);
                startActivity(intent);
            }
        });


    }


    public void AbreBanco(Activity activity) {
        bancoDados = activity.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
        bancoDados.getPath();
        //Log.i("bancodadosLocal", "Caminho " + bancoDados.getPath());
    }

    public void FechaBanco() {
        bancoDados.close();
    }

    public void toast(String aviso) {
        Toast.makeText(this, aviso, Toast.LENGTH_SHORT).show();
    }


}
