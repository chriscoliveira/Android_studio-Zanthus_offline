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

public class PesquisaFalhas extends Activity {
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
        setContentView(R.layout.tela_pesquisa);

        AbreBanco();


        final EditText edtPesquisa = (EditText) findViewById(R.id.edtpesquisa);

        edtPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorPesquisa = edtPesquisa.getText().toString();
                Log.i("valordapesquisa","Valor da pesquisa "+valorPesquisa);
                FiltroPesquisa(valorPesquisa);
            }
        });

        ListView MostrarDados = (ListView) findViewById(R.id.lvListagem);
        TodosRegistros();

    }


    public void AbreBanco()  {
        bancoDados = openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
        bancoDados.getPath();
        Log.i("bancodadosLocal", "Caminho " + bancoDados.getPath());
    }

    public void FechaBanco() {
        bancoDados.close();
    }

    public void toast(String aviso) {
        Toast.makeText(this, aviso, Toast.LENGTH_SHORT).show();
    }



    @SuppressWarnings("deprecation")
    public void TodosRegistros() {
        MostraDados = (ListView) findViewById(R.id.lvListagem);

        if (MostrarTodosRegistros())
        {
            adapterLista = new SimpleCursorAdapter(this, R.layout.tela_listagem_pesquisa, cursor,
                    new String[]{CODIGO,DESCRICAO},
                    new int[]{R.id.tvCodigo,R.id.tvDescricao});
            MostraDados.setAdapter(adapterLista); // executa a ação
        } else {
            toast("Não existem dados a exibir: ");
            MostraDados.setAdapter(null);
        }
    }

    private boolean MostrarTodosRegistros() {
        try {
            AbreBanco();

            cursor = bancoDados.query(NOME_TABELA, null, null, null, null, null, null);

            if (cursor.getCount() != 0)
            {
                cursor.moveToFirst();
                return true;
            } else
                return false;
        } catch (Exception er) {
            toast("Nao existe registros a exibir");
            return false;
        } finally {
            FechaBanco();
        }

    }
    public void FiltroPesquisa(String texto) {

        MostraDados = (ListView) findViewById(R.id.lvListagem);

        if (VerificaFiltrarPesquisa(texto)) // verifica
        // se a função é true

        {
            adapterLista = new SimpleCursorAdapter(this, R.layout.tela_listagem_pesquisa, cursor,
                    new String[]{CODIGO,DESCRICAO},
                    new int[]{R.id.tvCodigo,R.id.tvDescricao});
            MostraDados.setAdapter(adapterLista); // executa a ação
        } else {
            toast("Não existem dados a exibir: ");
            MostraDados.setAdapter(null);
        }
    }

    private boolean VerificaFiltrarPesquisa(String texto) {
        try {
            AbreBanco();
            String CRITERIO = "CODIGO like '%" + texto + "%' OR DESCRICAO  like '%" + texto + "%'";

            //Log.i("onde:: ", "'" + opcao + "'");
            Log.i("texto:: ", "'" + texto + "'");
            Log.i("criterio:: ", "" + CRITERIO);

            cursor = bancoDados.query(NOME_TABELA, null, CRITERIO, null,
                    null, null, null);

            if (cursor.getCount() != 0) // se existir registro
            {
                cursor.moveToFirst(); // movimenta para o 1º registro
                return true;
            } else
                return false;
        } catch (Exception er) {
            toast("Nao existe registros a exibir");
            return false;
        } finally {
            FechaBanco();
        }

    }



}
