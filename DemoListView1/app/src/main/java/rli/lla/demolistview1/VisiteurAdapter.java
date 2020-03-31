package rli.lla.demolistview1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class VisiteurAdapter extends ArrayAdapter<Visiteur> {

    public VisiteurAdapter(Context context, ArrayList<Visiteur> visiteurs) {
        super(context, 0, visiteurs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtenir le data Item à partir de la position
        Visiteur visiteur = getItem(position);

        //Préparer la View
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent, false);
        }

        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvNom = (TextView) convertView.findViewById(R.id.tvNom);
        TextView tvPrenom = (TextView) convertView.findViewById(R.id.tvPrenom);

        //Alimenter la ListView avec les Data
        tvId.setText(visiteur.getId());
        tvNom.setText(visiteur.getNom());
        tvPrenom.setText(visiteur.getPrenom());

        //Retourner la View
        return convertView;
    }
}