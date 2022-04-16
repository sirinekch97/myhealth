package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Admin extends AppCompatActivity {
  private SimpleCursorAdapter dataAdapter;
  private DatabaseHelper dbHelper;
  public final static  String id1 = "id";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);
    Button Btn = (Button)findViewById(R.id.bottom);
    Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i  = new Intent(Admin.this,AddProgAdmin.class);
        startActivity(i);

      }
    });

    dbHelper = new DatabaseHelper(this);
    displayListView();
  }
  private void displayListView() {
    Cursor cursor = dbHelper.fetchAllPrograms();

    // The desired columns to be bound
    String[] columns = new String[]{
      "num_p",
      "date_debut",
      "duree",
      "maladie",
      "patienti"
    };
    // the XML defined views which the data will be bound to
    int[] to = new int[]{
      R.id.num_p1,
      R.id.date_debut1,
      R.id.duree1,
      R.id.maladie1,
      R.id.patient,

    };

    // create the adapter using the cursor pointing to the desired data
    //as well as the layout information
    dataAdapter = new SimpleCursorAdapter(
      this, R.layout.layout_prog_admin,
      cursor,
      columns,
      to,
      0);

    ListView listView = (ListView) findViewById(R.id.listView2);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view,
                              int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String ProgramNum =
          cursor.getString(cursor.getColumnIndexOrThrow("num_p"));

        Intent p= new Intent(Admin.this, ProgGestP.class);
        int pp= Integer.parseInt(ProgramNum);
        p.putExtra(id1,pp);
        startActivity(p);

      }
    });

  }

}
