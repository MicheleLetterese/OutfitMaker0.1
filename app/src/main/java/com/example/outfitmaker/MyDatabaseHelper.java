package com.example.outfitmaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    //Stampa di tutti i dati

    Cursor readAllDataUtente(){
        String query="SELECT * FROM "+ TABLE_UTENTE;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }

    /* nel main
    *
    * MyDatabaseHelper db
    * ArrayList<String> utente_id, nome, cognome, email, password;
    *
    *
    * public void onCreate( Bundle savedIstanceState){
    *   super.onCreate(savedIstanceState);
    *   setContentView(R.layout.activity_main);
    *
    *   recycleView= findViewById(R.id.recycleView);
    *   add_button= findViewById(R.id.add_button);
    *   add_buttin=setOnClickListener((view)->{
    *           Intent intent= new Intent (MainActivity.this, AddActivity.class);
    *           startActivity(intent);
    *   }
    *
    * });
    *
    * db= new MyDatabaseHelper(MainActivity.this);
    * utente_id=new ArrayList<>();
    * nome=new ArrayList<>();
    * cognome=new ArrayList<>();
    * email=new ArrayList<>();
    * password=new ArrayList<>();
    *
    * storageDataInArrays();
    * CustomAdapter= new CustomAdapter(MainActivity.this, nome, cognome, email, password);
    * recyclerView.setAdapter(customAdapter);
    * recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this);
    * */



    Cursor readAllDataArmadio(){
        String query="SELECT * FROM "+ TABLE_ARMADIO;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataCapo(){
        String query="SELECT * FROM "+ TABLE_CAPO;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllData(){
        String query="SELECT * FROM "+ TABLE_OUTFIT;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query, null);
        }
        return cursor;
    }


    //modifica utente
    void updateData(String row_id, String nome, String surname, String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();

        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);

        long result=db.update(TABLE_UTENTE, cv, "id=?", new String[]{row_id});

        if(result==-1){
            Toast.makeText(context, "Aggiornamento fallito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Aggiornato con successo", Toast.LENGTH_SHORT).show();
        }
    }


    /*
    * Nel main
    *
    * Prima viene chiamato
    * getAndSetIntentData
    * MyDatabaseHelper db= new MyDatabaseHelper(UpdateActivity.this);
    * e solo dopo
    * db.updateData(id, nome, cognome, email, password);
    * */

    //Eliminazione utente

    void deleteUser(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result= db.delete(TABLE_UTENTE, "id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Eliminazione fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminazione avvenuta con successo", Toast.LENGTH_SHORT);
        }
    }


    void deleteArmadio(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result= db.delete(TABLE_ARMADIO, "id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Eliminazione fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminazione avvenuta con successo", Toast.LENGTH_SHORT);
        }
    }

    void deleteCapo(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result= db.delete(TABLE_CAPO, "id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Eliminazione fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminazione avvenuta con successo", Toast.LENGTH_SHORT);
        }
    }

    void deleteOutfit(String row_id){
        SQLiteDatabase db= this.getWritableDatabase();
        long result= db.delete(TABLE_OUTFIT, "id=?", new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Eliminazione fallita", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Eliminazione avvenuta con successo", Toast.LENGTH_SHORT);
        }
    }
}

