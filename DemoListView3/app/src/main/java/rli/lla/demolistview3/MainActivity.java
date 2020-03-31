package rli.lla.demolistview3;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //Initilisation des champs d'instance
    ListView simpleListView;
    //Tableau des noms des animaux
    String[] NomAnimal={"Lion","Tigre","Singe","Chien","Chat","Elephant"};
    //Tableau des images des animaux
    int[] ImageAnimal={R.drawable.lion,R.drawable.tigre,R.drawable.singe,R.drawable.chien,R.drawable.chat,R.drawable.elephant};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleListView=(ListView)findViewById(R.id.simpleListView);

        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

        for (int i=0;i<NomAnimal.length;i++) //Boucle pour balayer l'ensemble des images
        {
            HashMap<String,String> hashMap = new HashMap<>(); //Création d'un hashmap pour stocker les données dans des paires key, value
            hashMap.put("name",NomAnimal[i]);
            hashMap.put("image", ImageAnimal[i] +"");
            arrayList.add(hashMap); //Ajout du hashmap dans le ArrayList<>
        }
        String[] source={"name","image"}; //Tableau de chaînes
        int[] destination = {R.id.textView,R.id.imageView}; //tableau des id des composants

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.list_view_items,source,destination);  //Utilisation du simpleAdapter
        simpleListView.setAdapter(simpleAdapter);   //Associer le simpleAdapter au listView

        //Gestion des events sur les items de la liste
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),NomAnimal[i],Toast.LENGTH_LONG).show();
                //Un toast pour l'image sélectionné dans la liste
            }
        });

    }
}