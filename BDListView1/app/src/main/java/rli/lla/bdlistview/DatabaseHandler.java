package rli.lla.bdlistview;

 import android.content.ContentValues;
 import android.content.Context;
 import android.database.Cursor;
 import android.database.SQLException;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;
 import android.util.Log;

 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Locale;

 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_CODE = "code";
    public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_LOGIN = "login";

    private static final String TAG = "VisiteurDBAdapter";
    private DatabaseHandler mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "GSBVisteur";
    private static final String SQLITE_TABLE = "Visiteur";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_CODE + "," +
                    KEY_NOM + "," +
                    KEY_PRENOM + "," +
                    KEY_LOGIN + "," +
                    " UNIQUE (" + KEY_CODE +"));";

        DatabaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mCtx = null;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
            onCreate(db);
        }

    private static  DatabaseHandler mInstance = null;
    public static DatabaseHandler getmInstance(Context context){
        if(mInstance==null){
            mInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return mInstance;
    }

    public DatabaseHandler open() throws SQLException {
        mDbHelper = new DatabaseHandler(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createVisiteur(String code, String nom, String prenom, String login) {

        mDb = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_CODE, code);
        initialValues.put(KEY_NOM, nom);
        initialValues.put(KEY_PRENOM, prenom);
        initialValues.put(KEY_LOGIN, login);

        return mDb.insert(SQLITE_TABLE, null, initialValues);
    }

    public boolean deleteAllVisiteurs() {
        int doneDelete = 0;
        doneDelete = mDb.delete(SQLITE_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }

    public Cursor fetchVisiteurParNom(String inputText) throws SQLException {
        Log.w(TAG, inputText);
        Cursor mCursor = null;
        if (inputText == null  ||  inputText.length () == 0)  {
            mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID, KEY_CODE, KEY_NOM, KEY_PRENOM, KEY_LOGIN},
                    null, null, null, null, null);

        }
        else {
            mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID, KEY_CODE, KEY_NOM, KEY_PRENOM, KEY_LOGIN},
                    KEY_NOM + " like '%" + inputText + "%'", null,
                    null, null, null, null);
        }
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor fetchAllVisiteurs() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID, KEY_CODE, KEY_NOM, KEY_PRENOM, KEY_LOGIN},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void insertVisiteurs() {
        createVisiteur("a131", "Villechalane", "Louis", "lvillachane");
        createVisiteur("a17", "Andre", "David", "dandre");
        createVisiteur("a55", "Bedos", "Christian", "cbedos");
        createVisiteur("a93", "Tusseau", "Louis", "ltusseau");
        createVisiteur("b13", "Bentot", "Pascal", "pbentot");
        createVisiteur("b16", "Bioret", "Luc", "lbioret");
        createVisiteur("b19", "Bunisset", "Francis", "fbunisset");
        createVisiteur("b25", "Bunisset", "Denise", "dbunisset");
        createVisiteur("b28", "Cacheux", "Bernard", "bcacheux");
        createVisiteur("b34", "Cadic", "Eric", "ecadic");
    }
}

