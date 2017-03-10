package com.example.marcos.contatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Button btn = (Button) findViewById(R.id.btnCadastrar);
        dbHelper = new DatabaseHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                EditText txtNome = (EditText) findViewById(R.id.TextFieldNome);
                String nome = txtNome.getText().toString();
                intent.putExtra("nome", nome);

                EditText txtEmail = (EditText) findViewById(R.id.TextFieldEmailcad);
                String email = txtEmail.getText().toString();
                intent.putExtra("email", email);

                EditText txtEndereco = (EditText) findViewById(R.id.TextFieldEnd);
                String endereco = txtEndereco.getText().toString();
                intent.putExtra("endereco", endereco);

                EditText txtTelefone = (EditText) findViewById(R.id.TextFieldTel);
                String telefone = txtTelefone.getText().toString();
                intent.putExtra("telefone", telefone);

                EditText txtSenha = (EditText) findViewById(R.id.TextFieldSenCad);
                String senha = txtSenha.getText().toString();
                intent.putExtra("senha", senha);
                setResult(RESULT_OK, intent);

                EditText txtSenhaConf = (EditText) findViewById(R.id.TextFieldSenCadConf);
                String senhaconf = txtSenhaConf.getText().toString();
                intent.putExtra("senhaconf", senhaconf);
                setResult(RESULT_OK, intent);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("nome", nome);
                values.put("email", email);
                values.put("tel", telefone);
                values.put("end", endereco);
                values.put("pass", senha);
                long result = db.insert("usuario", null, values);

                if (result != -1) {
                    Toast.makeText(CadastroActivity.this, getString(R.string.text_bdok), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CadastroActivity.this, getString(R.string.text_bderro), Toast.LENGTH_SHORT).show();
                }

                finish();
            }

        });
        findViewById(R.id.btnCancelarCad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}

