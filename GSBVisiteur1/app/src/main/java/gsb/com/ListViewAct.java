package gsb.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import models.Visiteur;

public class ListViewAct extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_list);

        listView = (ListView)findViewById(R.id.listview);

        Visiteur visiteur1 = new Visiteur("500","LAROUSSI","Youcef", "youyou", "Azerty");
        Visiteur visiteur2 = new Visiteur("600","LAROUSSI","Meryam", "mymy", "Azerty");

        Visiteur[] tabVisiteur = new Visiteur[] {visiteur1, visiteur2};

        // android.R.layout.simple_list_item_1 is a constant predefined layout of Android.
        // used to create a ListView with simple ListItem (Only one TextView).

        ArrayAdapter<Visiteur> arrayAdapter
                = new ArrayAdapter<Visiteur>(this, android.R.layout.simple_list_item_1 , tabVisiteur);

        listView.setAdapter(arrayAdapter);

    }
}
