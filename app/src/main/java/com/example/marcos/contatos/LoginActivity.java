package com.example.marcos.contatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnCad = (Button) findViewById(R.id.login_btnCadastrar);
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivityForResult(it, 9999);
            }
        });
        Button btnLog = (Button) findViewById(R.id.login_btnLogin);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtEmailLogin = (EditText) findViewById(R.id.TextFieldEmailLogin);
                EditText txtSenhaLogin = (EditText) findViewById(R.id.TextFieldSenhaLogin);


                DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                Cursor cursor = db.rawQuery("SELECT email, pass FROM usuario WHERE email ='" + txtEmailLogin.getText().toString() + "'", null);
                String email = txtEmailLogin.getText().toString();
                String senha = txtSenhaLogin.getText().toString();

                if (cursor.moveToFirst()) {

                    if (cursor.getString(0).equals(email)) {
                        if (cursor.getString(1).equals(senha)) {
                            Intent it = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(it);
                        }  else {
                            Toast.makeText(LoginActivity.this, getString(R.string.text_errologin), Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.text_errologin), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String email = data.getExtras().getString("email");
        EditText txtEmailLogin = (EditText) findViewById(R.id.TextFieldEmailLogin);
        txtEmailLogin.setText(email);
    }
}
