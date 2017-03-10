package com.example.marcos.contatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {



        private static final String BANCO_DADOS = "contatos.db";
        private static int VERSAO = 1;

        public DatabaseHelper(Context context) {
            super(context, BANCO_DADOS, null, VERSAO);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY, nome TEXT, email TEXT, tel TEXT, end TEXT, pass TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
