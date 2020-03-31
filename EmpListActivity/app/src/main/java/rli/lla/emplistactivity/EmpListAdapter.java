package rli.lla.emplistactivity;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class EmpListAdapter extends CursorAdapter {

    public EmpListAdapter(Context context, Cursor cursor, int flag){
        super(context, cursor, 0);
    }

    public void bindView(View view, Context context, Cursor cursor) {

        TextView txtEmpName = (TextView) view.findViewById(R.id.txtEmpName);
        String EmpName = cursor.getString(cursor.getColumnIndex("EmpName"));
        txtEmpName.setText(EmpName);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.emp_items, parent,false);
    }
}
