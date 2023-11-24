package com.example.outfitmaker;

import android.provider.BaseColumns;

public class SchemaDB {
    // To prevent someone from accidentally instantiating the
    // schema class, give it an empty constructor.
    public SchemaDB() {
    }

    /* Inner class that defines the table contents */
    public static abstract class Utente implements BaseColumns {
        public static int COLUMN_ID ;
        public static final String TABLE_NAME = "utenti";
        public static  String COLUMN_NAME = "nome";
        public static  String COLUMN_COGNOME= "cognome";

        public static String COLUMN_EMAIL="email";
        public static String COLUMN_PASSWORD="password";
        public static String COLUMN_TELEFONO="telefono";
        public static String COLUMN_SESSO="sesso";
        public static boolean COLUMN_ADMIN;


    }

}
