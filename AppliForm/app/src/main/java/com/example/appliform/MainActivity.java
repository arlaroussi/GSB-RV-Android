package com.example.appliform;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.text.TextWatcher;
import android.text.Editable;
import android.app.AlertDialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appliform.R;

public class MainActivity extends AppCompatActivity {

    // La chaîne de caractères par défaut
    private final String defaut = "Cliquer sur 'Calculer TTC' ";

    // La chaîne de caractères de fin
    private final String fin = "Ok, c'est bon";

    private Button calculer = null;
    private Button raz = null;
    private EditText montht = null;
    private EditText tva = null;
    private EditText remise = null;
    private RadioGroup group = null;
    private RadioButton fidele;
    private RadioButton nonfidele;
    private TextView result = null;
    private CheckBox internat = null;
    private EditText devise = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère toutes les vues dont on a besoin
        this.calculer = (Button) findViewById(R.id.calculer);
        this.raz = (Button) findViewById(R.id.raz);
        this.tva = (EditText) findViewById(R.id.tva);
        this.tva.setTextSize(10);
        this.montht = (EditText) findViewById(R.id.montht);
        this.montht.setTextSize(10);
        this.remise = (EditText) findViewById(R.id.remise);
        this.remise.setTextSize(10);
        this.internat = (CheckBox) findViewById(R.id.internat);
        this.group = (RadioGroup) findViewById(R.id.group);
        this.group.clearCheck();
        this.fidele = (RadioButton) findViewById(R.id.fidele);
        this.nonfidele = (RadioButton) findViewById(R.id.nonfidele);
        this.result = (TextView) findViewById(R.id.montttc);
        this.devise = (EditText) findViewById(R.id.devise);

        this.remise.setEnabled(false);
        this.internat.setChecked(false);
        this.devise.setVisibility(View.INVISIBLE);

        // On attribue un listener adapté aux vues concernées
        this.calculer.setOnClickListener(calculerListener);
        this.raz.setOnClickListener(razListener);
        this.montht.addTextChangedListener(textWatcher);
        this.remise.addTextChangedListener(textWatcher);
        this.internat.setOnClickListener(checkedListener);
        this.group.setOnCheckedChangeListener(groupListener);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

   // Uniquement pour le bouton "calculer"
    private OnClickListener calculerListener = new OnClickListener() {
       @Override
       public void onClick(View v) {

           String t1 = montht.getText().toString();
           double vMontHT = Double.valueOf(t1);

           String t2 = tva.getText().toString();
           double vTVA = Double.valueOf(t2);

           double vRemise = 0;

           if (group.getCheckedRadioButtonId() == R.id.fidele) {

               remise.setEnabled(true);

               String t3 = remise.getText().toString();
               vRemise = Double.valueOf(t3);

           }

           double tva1 = vMontHT * vTVA;

           tva1 = tva1 / 100;

           double remise1 =  vMontHT * vRemise;
           remise1 = remise1 / 100;

           double vMontTTC = vMontHT + tva1 - remise1;

           String resultat = result.getText().toString() ;

           Bundle paquet = new Bundle() ;
           paquet.putString( "TotalTTC" , String.valueOf(vMontTTC)) ;
           Intent envoyer = new Intent(MainActivity.this, ActiviteSeconde.class) ;
           envoyer.putExtras(paquet) ;
           startActivity(envoyer);
       }

   };

       // Listener du bouton de remise à zéro
       private OnClickListener razListener = new OnClickListener() {
           @Override
           public void onClick(View v) {
               montht.setText("");
               remise.setText("");
               tva.setText("");
               devise.setVisibility(View.INVISIBLE);
               //group.clearCheck();
               internat.setChecked(false);
               result.setText(defaut);
               nonfidele.setChecked(true);
           }
       };

       // Listener du bouton de la devise
       private OnClickListener checkedListener = new OnClickListener() {
           @Override
           public void onClick(View v) {

               if (!(internat.isChecked())) {
                   devise.setVisibility(View.INVISIBLE);
               }
               else
               {
                   devise.setVisibility(View.VISIBLE);
               }
           }
       };

       private RadioGroup.OnCheckedChangeListener groupListener = new RadioGroup.OnCheckedChangeListener(){

        // Nous utilisons une classe anonyme
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            RadioButton bouton=(RadioButton)findViewById(checkedId);

            if (bouton.getId()==R.id.fidele){

                remise.setEnabled(true);
                Toast.makeText(MainActivity.this,bouton.getText(), Toast.LENGTH_SHORT).show();

            }
            else if (bouton.getId()==R.id.nonfidele) {

                remise.setEnabled(false);
                Toast.makeText(MainActivity.this,bouton.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    };

}