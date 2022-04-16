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

public class ProgTempA extends AppCompatActivity {
  private SimpleCursorAdapter dataAdapter;
  private DatabaseHelper dbHelper;
  public final static  String id1 = "id";

  Button addt;
  public  static  int p=0 ;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prog_temp2);
    Intent i = getIntent();

    final int id = i.getIntExtra(ProgMed.id1, 0);


    if (id!=0){
      p=id;}
    addt=(Button)findViewById(R.id.addtmpa);
    addt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent in  = new Intent(ProgTempA.this,AddPTA.class);
        in.putExtra(id1,p);


        startActivity(in);

      }
    });
    dbHelper = new DatabaseHelper(this);
    displayListView(p);
  }
  protected void onResume(int i) {
    super.onResume();

    displayListView(i);

  }

  private void displayListView(final int idc)  {
    Cursor cursor = dbHelper.fetchAllProgramtempsid(idc);

    // The desired columns to be bound
    String[] columns = new String[]{
      "num_temp",
      "degrés",


    };
    // the XML defined views which the data will be bound to
    int[] to = new int[]{
      R.id.numtp,
      R.id.dgre
    };

    // create the adapter using the cursor pointing to the desired data
    //as well as the layout information
    dataAdapter = new SimpleCursorAdapter(
      this, R.layout.layout_progtmp,
      cursor,
      columns,
      to,
      0);
    ListView listView = (ListView) findViewById(R.id.listView00);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view,
                              int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String t =
          cursor.getString(cursor.getColumnIndexOrThrow("num_temp"));
        dbHelper.DeleteProgTemp(t);
        onResume(idc);

      }
    });
  }}


