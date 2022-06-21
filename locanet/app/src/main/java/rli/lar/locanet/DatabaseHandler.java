package rli.lar.locanet;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;
import android.content.Context;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "location";
    public static final int    DB_VERSION = 1;
    public static final String TAB_NAME = "vehicule";
    public static final String C_ID = "id";
    public static final String C_MARQUE= "marque";
    public static final String C_MODELE = "modele";
    public static final String C_COULEUR = "couleur";
    public static final String C_IMMAT = "immatriculation";
    public static final int    NUM_C_ID = 0;
    public static final int    NUM_C_MARQUE = 1;
    public static final int    NUM_C_MODELE = 2;
    public static final int    NUM_C_COULEUR = 3;
    public static final int    NUM_C_IMMAT = 4;

    private static final String CREATE_BDD = "CREATE TABLE " + TAB_NAME + " ("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_MARQUE + " TEXT NOT NULL, "
            + C_IMMAT + " TEXT NOT NULL, "
            + C_MODELE + " TEXT NOT NULL,"
            + C_COULEUR + " INTEGER NOT NULL)";

    private static final String TAG = "VehiculeAdapter";

    private DatabaseHandler mDbHelper;

    private Context mCtx=null;


    private static  DatabaseHandler mInstance = null;

    public static DatabaseHandler getmInstance(Context context){
        if(mInstance==null){
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mCtx = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG, CREATE_BDD);
        // on crée la table
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TAB_NAME);
        onCreate(db);
    }

    public DatabaseHandler open() throws SQLException {
        mDbHelper = new DatabaseHandler(mCtx);
        mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVehicule(SQLiteDatabase mDb, String marque, String modele, String couleur, String immat) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(C_MARQUE, marque);
        initialValues.put(C_MODELE, modele);
        initialValues.put(C_COULEUR, couleur);
        initialValues.put(C_IMMAT, immat);

        return mDb.insert(TAB_NAME, null, initialValues);
    }

    public boolean deleteAllVehicules(SQLiteDatabase mDb) {
        int doneDelete = 0;
        doneDelete = mDb.delete(TAB_NAME, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public Cursor fetchVehiculeParImmat(SQLiteDatabase mDb, String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(TAB_NAME, new String[] {C_ID, C_IMMAT, C_MARQUE, C_MODELE, C_COULEUR},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, TAB_NAME, new String[] {C_ID, C_IMMAT, C_MARQUE, C_MODELE, C_COULEUR},
                    C_IMMAT + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllVehicules(SQLiteDatabase mDb) {

        Cursor mCursor = mDb.rawQuery("SELECT * FROM "+TAB_NAME, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertVehicules(SQLiteDatabase mDb) {
        createVehicule(mDb, "Renault", "Clio", "Gris", "AF-355-DA");
        createVehicule( mDb,"DT-258-LF", "VW", "Passat", "Bleu");
        createVehicule( mDb,"EA-117-RT", "BMW", "525", "Noir");
        createVehicule( mDb,"BZ-750-XT", "AUDI", "A4", "Rouge");
        createVehicule( mDb,"CA-505-VT", "Peugeot", "308", "Gris");
    }
}

