package rli.lla.loginwindows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gsb.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOM = "visiteur";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM= "prenom";
    private static final String COL_EMAIL= "email";
    private static final String COL_PASSWORD= "password";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NOM + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_EMAIL + " TEXT NOT NULL, "
            + COL_NOM + " TEXT NOT NULL, " + COL_PRENOM + " TEXT NOT NULL, " + COL_PASSWORD + " TEXT );";

    SQLiteDatabase bdd;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
     //comme ça lorsque je change la version les id repartent de 0
            db.execSQL("DROP TABLE " + TABLE_NOM + ";");
            this.onCreate(db);
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public boolean verifMail(String email) {
        bdd = this.getReadableDatabase();
        Cursor curs = bdd.rawQuery("Select * from Visiteur where email = ? ", new String []{email} );
        if (curs.getCount() == 1)
            return true;
        else return
            false;
        }

    public long createVisiteur(String email, String nom, String prenom, String password) {
        bdd = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_EMAIL, email);
        initialValues.put(COL_NOM, nom);
        initialValues.put(COL_PRENOM, prenom);
        initialValues.put(COL_PASSWORD, password);
        return bdd.insert(TABLE_NOM, null, initialValues);
    }

    public void insertVisiteurs() {
        createVisiteur("a131@test.fr", "Villechalane", "Louis", "lvillachane");
        createVisiteur("a17@test.fr", "Andre", "David", "dandre");
        createVisiteur("a55@test.fr", "Bedos", "Christian", "cbedos");
        createVisiteur("a93@test.fr", "Tusseau", "Louis", "ltusseau");
        createVisiteur("b13@test.fr", "Bentot", "Pascal", "pbentot");
        createVisiteur("b16@test.fr", "Bioret", "Luc", "lbioret");
        createVisiteur("b19@test.fr", "Bunisset", "Francis", "fbunisset");
        createVisiteur("b25@test.fr", "Bunisset", "Denise", "dbunisset");
        createVisiteur("b28@test.fr", "Cacheux", "Bernard", "bcacheux");
        createVisiteur("b34@test.fr", "Cadic", "Eric", "ecadic");
    }
} //Fin de la classe

