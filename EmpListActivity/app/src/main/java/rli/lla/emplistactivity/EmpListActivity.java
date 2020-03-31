package rli.lla.emplistactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class EmpListActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private EmpListAdapter empListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_list);

        db =DatabaseHandler.getmInstance(getApplicationContext());

        ListView mListView = (ListView) findViewById(R.id.list);

        db.insertIntoEmpInfo("test","test","test","test","test");

        Cursor cursor = db.getEmpList();

        if(cursor!=null){
            empListAdapter = new EmpListAdapter(getApplicationContext(), cursor, 0);
            mListView.setAdapter(empListAdapter);
        }

    }
    }

