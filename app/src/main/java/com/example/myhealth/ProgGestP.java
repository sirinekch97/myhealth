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
import android.widget.Toast;

public class ProgGestP extends AppCompatActivity {
  private SimpleCursorAdapter dataAdapter;
  private DatabaseHelper dbHelper;
  public final static  String id1 = "id1";
  public final static  String id2 ="id2";
  Button tep,addmed, sp;
  public  static  int p=0 ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_prog_gest_p);
    addmed=(Button)findViewById(R.id.addmeda);
    tep =(Button)findViewById(R.id.tempa);
    sp=(Button)findViewById(R.id.suppa);
    Intent i=getIntent();
    final int idp = i.getIntExtra(Admin.id1, 0);
    if (idp!=0){
      p=idp;}
    tep.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {


        Intent intenttep=new Intent(ProgGestP.this,ProgTempA.class);
        intenttep.putExtra(id1,p);
        startActivity(intenttep);
      }
    });
    sp.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        dbHelper.Deleteprogram(p);
        Intent intenttep=new Intent(ProgGestP.this,Admin.class);
        intenttep.putExtra(id1,p);
        startActivity(intenttep);

      }
    });
    addmed.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {


        Intent intenttep=new Intent(getApplicationContext(),AddMedA.class);
        intenttep.putExtra(id1,p);
        startActivity(intenttep);
      }
    });

    dbHelper = new DatabaseHelper(this);
    displayListView(p);
  }
  @Override
  protected void onResume() {
    super.onResume();
    displayListView(p);
  }
  //}

  private void displayListView(int idc) {
    Cursor cursor = dbHelper.fetchAllMedsPid(idc);

    // The desired columns to be bound
    String[] columns = new String[]{
      "ref_med",
      "date_deb_cons",
      "duree",

    };
    // the XML defined views which the data will be bound to
    int[] to = new int[]{
      R.id.ref_med1,
      R.id.date_deb_cons,
      R.id.duree
    };

    // create the adapter using the cursor pointing to the desired data
    //as well as the layout information
    dataAdapter = new SimpleCursorAdapter(
      this, R.layout.layout_program_med_info,
      cursor,
      columns,
      to,
      0);
    ListView listView = (ListView) findViewById(R.id.listView3a);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view,
                              int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String Medref =
          cursor.getString(cursor.getColumnIndexOrThrow("ref_med"));
        Intent pi= new Intent(ProgGestP.this, ProgMedPriseA.class);
        int mr=Integer.parseInt(Medref);

        pi.putExtra(id1,mr);  //ref_med
        pi.putExtra(id2,p);  //progn

        startActivity(pi);

      }
    });

  }
}
