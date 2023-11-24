package com.example.outfitmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME="OutfitMaker.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="user";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_SURNAME="surname";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PASSWORD="password";

    //TABELLA CAPO
    private static final String COLUMN_COLORE="colore";
    private static final String COLUMN_TIPO="tipo";

    // Nomi delle tabelle
    public static final String TABLE_UTENTE = "utente";
    public static final String TABLE_ARMADIO = "armadio";
    public static final String TABLE_CAPO = "capo";
    public static final String TABLE_OUTFIT = "outfit";

    //Tabella outfit
    private static final String COLUMN_TIPOLOGIA="tipologia";
    // Creazione della tabella utente
    private static final String CREATE_TABLE_UTENTE =
            "CREATE TABLE " + TABLE_UTENTE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_SURNAME+ "TEXT NOT NULL, " +
                    COLUMN_EMAIL+ " TEXT NOT NULL, "+
                    COLUMN_PASSWORD+ "TEXT NOT NULL);";

    // Creazione della tabella armadio
    private static final String CREATE_TABLE_ARMADIO =
            "CREATE TABLE " + TABLE_ARMADIO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "utente_id INTEGER, " +
                    "FOREIGN KEY(utente_id) REFERENCES " + TABLE_UTENTE + "(" + COLUMN_ID + "));";

    // Creazione della tabella capo
    private static final String CREATE_TABLE_CAPO =
            "CREATE TABLE " + TABLE_CAPO + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COLORE+" TEXT NOT NULL,"+
                    COLUMN_TIPO+ " TEXT NOT NULL,"+
                    "armadio_id INTEGER, " +
                    "FOREIGN KEY(armadio_id) REFERENCES "+ TABLE_ARMADIO+ "("+ COLUMN_ID+"));";


    // Creazione della tabella outfit
    private static final String CREATE_TABLE_OUTFIT =
            "CREATE TABLE " + TABLE_OUTFIT + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TIPOLOGIA+ "TEXT NOT NULL);";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_UTENTE);
        db.execSQL(CREATE_TABLE_ARMADIO);
        db.execSQL(CREATE_TABLE_CAPO);
        db.execSQL(CREATE_TABLE_OUTFIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    void addUser(String name, String surname, String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        long result=db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context,"Inserimento Fallito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Inserimento avvenuto con successo", Toast.LENGTH_SHORT).show();
        }
    }

    void addArmadio( int idUser){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("utente_id",idUser);

        long result=db.insert(TABLE_ARMADIO, null, cv);

        if(result==-1){
            Toast.makeText(context, "Creazione Armadio Fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Avvenuta creazione armadio", Toast.LENGTH_SHORT).show();
        }
    }

    void addCapo(String colore, String tipo, int armadio_id){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_COLORE, colore);
        cv.put(COLUMN_TIPO, tipo);
        cv.put("armadio_id", armadio_id);

        long result= db.insert(TABLE_CAPO, null, cv);
        if(result==-1){
            Toast.makeText(context, "Aggiunta capo fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiunta capo avvenuta con successo", Toast.LENGTH_SHORT).show();
        }
    }
}