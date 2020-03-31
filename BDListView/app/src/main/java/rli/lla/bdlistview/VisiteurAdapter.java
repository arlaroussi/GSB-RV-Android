package rli.lla.bdlistview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class VisiteurAdapter extends CursorAdapter {

       public VisiteurAdapter(Context context, Cursor cursor, int flag){
            super(context, cursor, 0);
        }

        public void bindView(View view, Context context, Cursor cursor){

            TextView visCode = (TextView) view.findViewById(R.id.tvCode);
            TextView visNom = (TextView) view.findViewById(R.id.tvNom);
            TextView visPrenom = (TextView) view.findViewById(R.id.tvPrenom);
            TextView visLogin = (TextView) view.findViewById(R.id.tvLogin);

            String code = cursor.getString(cursor.getColumnIndexOrThrow("code"));
            String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
            String prenom = cursor.getString(cursor.getColumnIndexOrThrow("prenom"));
            String login = cursor.getString(cursor.getColumnIndexOrThrow("login"));

            visCode.setText(code);
            visNom.setText(nom);
            visPrenom.setText(prenom);
            visLogin.setText(login);
        }

        public View newView(Context context, Cursor cursor, ViewGroup parent){
            return LayoutInflater.from(context).inflate(R.layout.visiteur_items, parent,false);
        }
    }

