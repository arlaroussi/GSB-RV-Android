package gsb.com;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

    public class VisiteurCursorAdapter extends CursorAdapter
    {
        public VisiteurCursorAdapter(Context context, Cursor c) {
            super(context, c, 0);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            // Find fields to populate in inflated template
            TextView tvNom = (TextView) view.findViewById(R.id.tvNom);
            TextView tvPrenom = (TextView) view.findViewById(R.id.tvPrenom);

            // Extract properties from cursor
            String body = cursor.getString(cursor.getColumnIndex("nom"));
            int priority = cursor.getInt(cursor.getColumnIndexOrThrow("prenom"));

            // Populate fields with extracted properties
            tvNom.setText(body);
            tvPrenom.setText(String.valueOf(priority));
        }
    }
