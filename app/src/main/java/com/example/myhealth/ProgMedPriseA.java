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

public class ProgMedPriseA extends AppCompatActivity {
  private SimpleCursorAdapter dataAdapter;
  private DatabaseHelper dbHelper;
  public final static  String id1 = "id";
  public  static  int p=0 ;
  public  static  int q=0 ;

  Button addpr, sm;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prog_med_prise2);
    Intent i = getIntent();
    final int idx = i.getIntExtra(ProgGestP.id2, 0);
    if (idx!=0){
      q=idx;}
    final int idp = i.getIntExtra(ProgGestP.id1, 0);
    if (idp!=0){
      p=idp;}
    sm= (Button)findViewById(R.id.smeda);
    sm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dbHelper.Deleteprogrammed(p,q);
        Intent in  = new Intent(ProgMedPriseA.this,ProgGestP.class);


        startActivity(in);

      }
    });
    addpr= (Button)findViewById(R.id.bottompa);
    addpr.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent in  = new Intent(ProgMedPriseA.this,AddMedPrissA.class);
        in.putExtra(id1,p);


        startActivity(in);

      }
    });
    dbHelper = new DatabaseHelper(this);

    displayListView(p);

  }
  protected void onResume() {
    super.onResume();

    displayListView(p);

  }
  protected void onResume(int i) {
    super.onResume();

    displayListView(i);

  }

  private void displayListView(final int idc) {
    Cursor cursor = dbHelper.fetchAllMedsPrisesid(idc);

    // The desired columns to be bound
    String[] columns = new String[]{
      "num_prise",
      "description",
      "date",
      "heure",
      "qté",

    };
    // the XML defined views which the data will be bound to
    int[] to = new int[]{
      R.id.num_prise,
      R.id.desc,
      R.id.date,
      R.id.heure,
      R.id.qtt,

    };

    // create the adapter using the cursor pointing to the desired data
    //as well as the layout information
    dataAdapter = new SimpleCursorAdapter(
      this, R.layout.layout_medprise,
      cursor,
      columns,
      to,
      0);

    ListView listView = (ListView) findViewById(R.id.listView5a);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view,
                              int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String Prisenum =
          cursor.getString(cursor.getColumnIndexOrThrow("num_prise"));
        dbHelper.Deletemedprise(Prisenum);
        onResume(idc);

      }
    });

  }
}
