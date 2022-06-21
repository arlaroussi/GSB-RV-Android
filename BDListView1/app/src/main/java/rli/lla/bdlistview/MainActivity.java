package rli.lla.bdlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private VisiteurAdapter dataAdapter;
    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseHandler.getmInstance(getApplicationContext());


        //Ajouter des données à la table
        db.insertVisiteurs();

        mListView = (ListView) findViewById(R.id.listView);

        Cursor cursor = db.fetchAllVisiteurs();

        if(cursor != null){
            dataAdapter = new VisiteurAdapter(getApplicationContext(), cursor);
            mListView.setAdapter(dataAdapter);
        }
        Toast.makeText(this, "Data has been saved successfully!", Toast.LENGTH_LONG);


    }


}



