package rli.lla.emplistactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
    import android.content.ContentValues;
 import android.content.Context;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.Locale;

    public class DatabaseHandler extends SQLiteOpenHelper {

        private static final int DatabaseVersion=1;
        private static final String DatabaseName="db_testSql";
        private static final String CreateTable_EmpInfo="Create Table EmpInfo(ID Integer Primary Key AutoIncrement,EmpName Text, Address Text,City Text, State Text, Mobile Text, CreatedOn DateTime)";

        private static  DatabaseHandler mInstance = null;
        public static DatabaseHandler getmInstance(Context context){
            if(mInstance==null){
                mInstance = new DatabaseHandler(context.getApplicationContext());
            }
            return mInstance;
        }

        public DatabaseHandler(Context context){
            super(context, DatabaseName, null, DatabaseVersion);
        }
        @Override
        public  void onCreate(SQLiteDatabase db){
            db.execSQL(CreateTable_EmpInfo);
        }
        @Override
        public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            onCreate(db);
        }

        //To Get Current Datetime //
        private String getDateTime(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault() );
            Date date= new Date();
            return  dateFormat.format(date);
        }
        //Insert Record in EmpInfo Table //
        public long insertIntoEmpInfo(String EmpName, String Address, String City, String State, String Mobile){

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("EmpName",EmpName);
            values.put("Address",Address);
            values.put("City",City  );
            values.put("State",State);
            values.put("Mobile",Mobile);
            values.put("CreatedOn",getDateTime());

            long id = db.insert("EmpInfo",null,values);
            return id;
        }

        public Cursor getEmpList(){
            SQLiteDatabase db   = this.getWritableDatabase();
            String query = "Select ID _id, EmpName from EmpInfo";
            Cursor cursor = db.rawQuery(query, null);
            return  cursor;

        }

    }
