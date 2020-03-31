package rli.lla.loginwindows;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gsb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOM = "visiteur";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

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

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public boolean deleteAllVisiteurs() {
        bdd= this.getWritableDatabase();
        int doneDelete = 0;
        doneDelete = bdd.delete(TABLE_NOM, null, null);
        return doneDelete > 0;
    }

    public Cursor fetchVisiteurParNom(String inputText) throws SQLException {
        Cursor mCursor = null;
        if (inputText == null || inputText.length() == 0) {
            mCursor = bdd.query(TABLE_NOM, new String[]{COL_ID, COL_EMAIL, COL_NOM, COL_PRENOM, COL_PASSWORD},
                    null, null, null, null, null);

        } else {
            mCursor = bdd.query(true, TABLE_NOM, new String[]{COL_ID, COL_EMAIL, COL_NOM, COL_PRENOM, COL_PASSWORD},
                    COL_NOM + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllVisiteurs() {
        Cursor mCursor = bdd.query(TABLE_NOM, new String[]{COL_ID, COL_EMAIL, COL_NOM, COL_PRENOM, COL_PASSWORD},
                null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public ArrayList<String> getAllVisiteurs() {

        ArrayList<String> listeVisiteurs = new ArrayList<String>();
        bdd = this.getReadableDatabase();

        bdd.beginTransaction();
        try {
            String req = "SELECT * FROM " + TABLE_NOM;

            Cursor curs = bdd.rawQuery(req, null);
            if (curs.getCount() > 0) {
                while (curs.moveToNext()) {
                    String valeur = curs.getString(curs.getColumnIndex("email"));
                    listeVisiteurs.add(valeur);
                }
                bdd.setTransactionSuccessful();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bdd.endTransaction();
            bdd.close();
        }
        return listeVisiteurs;
    }

    public boolean verifMail(String email, String password) {
        bdd = this.getReadableDatabase();
        Cursor curs = bdd.rawQuery("Select * from Visiteur where email = ? and password = ?", new String []{email, password} );
        if (curs.getCount() == 0)
            return true;
        else return false;
    }


    public void createVisiteur(String email, String nom, String prenom, String password) {
        bdd = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        bdd.beginTransaction();
        try {
            initialValues.put(COL_EMAIL, email);
            initialValues.put(COL_NOM, nom);
            initialValues.put(COL_PRENOM, prenom);
            initialValues.put(COL_PASSWORD, password);
            bdd.insert(TABLE_NOM, null, initialValues);
            bdd.setTransactionSuccessful();
        }
        catch (Exception e) { e.printStackTrace(); }

        finally {
            bdd.endTransaction();
            bdd.close();
        }
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

