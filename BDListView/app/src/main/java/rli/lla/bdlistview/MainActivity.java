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
    private DatabaseHandler dbHelper;
    private VisiteurAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHandler(this);
        dbHelper.open();

        //Vider la table
        dbHelper.deleteAllVisiteurs();

        //Ajouter des données à la table
        dbHelper.insertVisiteurs();

        //Générer un ListView lié à la BDD
        displayListView();
   }

    private void displayListView() {

        Cursor cursor = dbHelper.fetchAllVisiteurs();

        //Créer un adapter utilisant le CursorAdapter qui pointera sur les data de ta table
        dataAdapter = new VisiteurAdapter(this, cursor, 0);

        ListView listView = (ListView) findViewById(R.id.listView);

        // Associer l'adapter au ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String visiteurNom =
                        cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                Toast.makeText(getApplicationContext(),
                        visiteurNom, Toast.LENGTH_SHORT).show();
            }
        });

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
                return dbHelper.fetchVisiteurParNom(constraint.toString());
            }
        });
    }
}


