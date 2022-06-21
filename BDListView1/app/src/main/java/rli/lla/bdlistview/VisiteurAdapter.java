package rli.lla.bdlistview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class VisiteurAdapter extends BaseAdapter {

    public VisiteurAdapter(Context context, ArrayList<Visiteur> visiteurs) {
        super(context, 0, visiteurs);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Obtenir la donnée à partir de la position
        Visiteur visiteur = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.visiteur_items, parent, false);
        }

        // Lookup view for data population

        TextView code = (TextView) convertView.findViewById(R.id.tvId);
        TextView nom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView pren = (TextView) convertView.findViewById(R.id.tvPrenom);
        TextView login = (TextView) convertView.findViewById(R.id.tvLogin);

        // Populate the data into the template view using the data object

        code.setText(visiteur.getCode());
        nom.setText(visiteur.getNom());
        pren.setText(visiteur.getPrenom());
        login.setText(visiteur.getLogin());

        // Return the completed view to render on screen

        return convertView;

    }

}
