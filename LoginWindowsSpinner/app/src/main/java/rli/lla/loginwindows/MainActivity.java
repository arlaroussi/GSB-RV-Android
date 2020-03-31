package rli.lla.loginwindows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etPassword;
    Spinner spEmail;
    Button btValider, btReset;
    DatabaseHelper dbh;
    Visiteur visiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spEmail = (Spinner) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        btValider = (Button) findViewById(R.id.valider);
        btReset = (Button) findViewById(R.id.annuler);

        dbh = new DatabaseHelper(getApplicationContext());

        dbh.deleteAllVisiteurs();

        dbh.insertVisiteurs();

        ArrayList<String> listeVisiteurs = dbh.getAllVisiteurs();

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.spinner_lay, R.id.item,listeVisiteurs);

        spEmail.setAdapter(adapter);

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPassword.setText("");
            }
        });

        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = spEmail.getSelectedItem().toString();
                String password = etPassword.getText().toString();
                System.out.println(email);
                if (email.equals("") || password.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Champs vides", Toast.LENGTH_LONG).show();
                }
                else {
                        boolean result = dbh.verifMail(email, password);
                        if (result == true)
                                Toast.makeText(getApplicationContext(),"Email ou mot de passe erroné",Toast.LENGTH_LONG).show();
                        else  {
                             Intent accueil = new Intent(MainActivity.this,AccueilActivity.class);
                            startActivity(accueil);
                        }
                    }
                }
        });
    }
} //Fin de la classe
