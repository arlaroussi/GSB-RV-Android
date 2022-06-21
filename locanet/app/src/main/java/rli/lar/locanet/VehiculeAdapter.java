package rli.lar.locanet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VehiculeAdapter extends CursorAdapter {

    public VehiculeAdapter(Context context, Cursor cursor, int flag) {
        super(context, cursor, 0);
    }

    public void bindView(View view, Context context, Cursor cursor) {

        TextView vehImmat = (TextView) view.findViewById(R.id.tvImmat);
        TextView vehMarque = (TextView) view.findViewById(R.id.tvMarque);
        TextView vehModele = (TextView) view.findViewById(R.id.tvModele);

        //String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String immat = cursor.getString(cursor.getColumnIndexOrThrow("immat"));
        String marque = cursor.getString(cursor.getColumnIndexOrThrow("marque"));
        String modele = cursor.getString(cursor.getColumnIndexOrThrow("modele"));

        vehImmat.setText(immat);
        vehMarque.setText(marque);
        vehModele.setText(modele);

    }

        public View newView(Context context, Cursor cursor, ViewGroup parent) {
               return LayoutInflater.from(context).inflate(R.layout.vehicule_items, parent,false);
        }
    }

