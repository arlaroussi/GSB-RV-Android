package com.example.appliform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActiviteSeconde extends AppCompatActivity {

    private TextView mntTTC;
    private Button retour = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_seconde);

        mntTTC = (TextView) findViewById(R.id.mntTTC);
        retour = (Button) findViewById(R.id.retour);

        Bundle paquet = this.getIntent().getExtras();
        String mmntTTC = paquet.getString("TotalTTC");
        mntTTC.setText(mmntTTC);

        this.retour.setOnClickListener(retourListener);
    }

    private OnClickListener retourListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent1 = new Intent(ActiviteSeconde.this, MainActivity.class);
            startActivity(intent1);
        }
    };
}