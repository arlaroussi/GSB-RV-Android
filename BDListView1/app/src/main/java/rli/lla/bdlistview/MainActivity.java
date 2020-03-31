package rli.lla.bdlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = DatabaseHandler.getmInstance(getApplicationContext());

        //Ajouter des données à la table
        db.insertVisiteurs();

        ListView mListView = (ListView) findViewById(R.id.listView);

        Cursor cursor = db.fetchAllVisiteurs();

        if(cursor != null){
            dataAdapter = new VisiteurAdapter(getApplicationContext(), cursor, 0);
            mListView.setAdapter(dataAdapter);
        }
        EditText monFilter = (EditText) findViewById(R.id.myFilter);

        monFilter.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {  }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {  }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchVisiteurParNom(constraint.toString());
            }
        });
    }
}



