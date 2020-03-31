package gsb.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import models.Visiteur;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        EditText edtNom, edtPrenom, edtId, edtLogin, edtPassword;
        Button btnAfficher, btnSupprimer, btnAjouter, btnAffichertout, btsReset, btnSuivant, btnPrecedent, btnReset, btnPremier;

        VisiteurDAO visiteurDAO;
        SQLiteDatabase bdd;
        private Visiteur visiteur;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                //ArrayList<Visiteur> arVisiteur = new ArrayList<Visiteur>();

                //Création d'une instance de ma classe VisiteurDAO
                visiteurDAO = new VisiteurDAO(this);

                edtId = (EditText) findViewById(R.id.edtId);
                edtNom = (EditText) findViewById(R.id.edtNom);
                edtPrenom = (EditText) findViewById(R.id.edtPrenom);
                edtLogin = (EditText) findViewById(R.id.edtLogin);
                edtPassword = (EditText) findViewById(R.id.edtPassword);

                edtPrenom = (EditText) findViewById(R.id.edtPrenom);
                btnAjouter = (Button) findViewById(R.id.btnAjouter);
                btnSupprimer = (Button) findViewById(R.id.btnSupprimer);
                btnAfficher = (Button) findViewById(R.id.btnAfficher);
                btnAffichertout = (Button) findViewById(R.id.btnAffichertout);
                btnSuivant = (Button) findViewById(R.id.btnSuivant);
                btnPrecedent = (Button) findViewById(R.id.btnPrecedent);
                btnPremier = (Button) findViewById(R.id.btnPremier);
                btnReset = (Button) findViewById(R.id.btnReset);
                btnAjouter.setOnClickListener(this);
                btnSupprimer.setOnClickListener(this);
                btnAffichertout.setOnClickListener(this);
                btnSupprimer.setOnClickListener(this);
                btnAfficher.setOnClickListener(this);
                btnSuivant.setOnClickListener(this);
                btnPrecedent.setOnClickListener(this);
                btnPremier.setOnClickListener(this);
                btnReset.setOnClickListener(this);
        } //Fin de la méthode onCreate()

        public void onClick(View view) {
                if (view == btnAjouter) {
                        if (edtNom.getText().toString().trim().length() == 0 || edtPrenom.getText().toString().trim().length() == 0) {
                                Toast.makeText(this, "Champs vides", Toast.LENGTH_LONG).show();
                                return;
                        }
                        //Création d'un Visiteur
                        visiteur = new Visiteur(edtId.getText().toString(), edtNom.getText().toString(), edtPrenom.getText().toString(), edtLogin.getText().toString(), edtPassword.getText().toString());

                        //On ouvre la base de données pour écrire dedans
                        visiteurDAO.open();

                        //On insère le visiteur que l'on vient de créer
                        visiteurDAO.insertVisiteur(visiteur);
                        Toast.makeText(this, visiteur.getNom() + " a été créé", Toast.LENGTH_LONG).show();

                } //Fin bouton Ajouter

                if (view == btnAffichertout) {

                        String requete = "SELECT * FROM visiteur";

                        bdd = visiteurDAO.open();

                        Cursor cur = bdd.rawQuery(requete, null);

                        if (cur.getCount() == 0) {
                                Toast.makeText(this, "Champs vides", Toast.LENGTH_LONG).show();
                                return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (cur.moveToNext()) {
                                buffer.append("Id: " + cur.getString(0) + "\n");
                                buffer.append("Non: " + cur.getString(1) + "\n");
                                buffer.append("Prénom: " + cur.getString(2) + "\n");
                        }
                        cur.close();
                        Toast.makeText(this, "Les visiteurs " + buffer.toString(), Toast.LENGTH_LONG).show();
                } //----fin afficher tout

                if (view == btnReset) {
                        edtId.setText(" ");
                        edtNom.setText("");
                        edtPrenom.setText(" ");
                        edtLogin.setText(" ");
                        edtPassword.setText(" ");

                } //----fin Effacer champs

                if (view == btnSupprimer) {

                        if(edtId.getText().toString().trim().length()==0)
                        {
                                Toast.makeText(this, " Erreur de saisie", Toast.LENGTH_LONG).show();
                                return;
                        }


                        String requete = "SELECT * FROM visiteur WHERE id="+ edtId.getText();

                        bdd = visiteurDAO.open();

                        Cursor cur = bdd.rawQuery(requete, null);

                        Visiteur visiteur = visiteurDAO.cursorToVisiteur(cur);

                        if(cur.moveToFirst())   {
                                visiteurDAO.removeVisiteurWithID(edtId.getText().toString());
                                Toast.makeText(this, visiteur.getNom() + " a été supprimé", Toast.LENGTH_LONG).show();
                        }
                        else    {
                                Toast.makeText(this, "Id saisi erroné", Toast.LENGTH_LONG).show();
                        }
               } // fin supprimer


                }
        }