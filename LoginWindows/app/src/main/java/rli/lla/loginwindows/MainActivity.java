package rli.lla.loginwindows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMail, etPassword;
    Button btValider, btReset;
    DatabaseHelper dbh;
    Visiteur visiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        btValider = (Button) findViewById(R.id.valider);
        btReset = (Button) findViewById(R.id.annuler);

        dbh = new DatabaseHelper(getApplicationContext());

        dbh.insertVisiteurs();

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMail.setText("");
                etPassword.setText("");
            }
        });

        btValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.equals("") || password.equals("") ) {
                    Toast.makeText(getApplicationContext(), "Champs vides", Toast.LENGTH_LONG).show();
                }
                else {
                        boolean result = dbh.verifMail(email);
                        if (result == false)
                                Toast.makeText(getApplicationContext(),"Email erroné",Toast.LENGTH_LONG).show();
                        else  {
                            Intent accueil = new Intent(MainActivity.this,AccueilActivity.class);
                            startActivity(accueil);
                        }
                    }
                }
        });
    }
} //Fin de la classe
