package rli.lla.demolistview1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construire la data source
        ArrayList<Visiteur> listeItem = new ArrayList<Visiteur>();

// Créer l'adapteur pour convertir les data en View
        VisiteurAdapter adapter = new VisiteurAdapter(this, listeItem);

// Associer l'adapteur à la ListView
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        Visiteur v1 = new Visiteur("100", "Laroussi", "Youyou");
        adapter.add(v1);
    }
}
