package gsb.com;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BDDSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gsb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_VISITEUR = "visiteur";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM= "prenom";
    private static final String COL_LOGIN= "login";
    private static final String COL_PASSWORD= "password";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_VISITEUR + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL, "
            + COL_PRENOM + " TEXT NOT NULL, " + COL_LOGIN + " TEXT NOT NULL, " + COL_PASSWORD + " TEXT );";

    public BDDSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_VISITEUR + ";");
        this.onCreate(db);
    }

}