package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import android.content.Intent;

public class Client extends AppCompatActivity {
  private SimpleCursorAdapter dataAdapter;
  private DatabaseHelper dbHelper;
  public final static  String id1 = "id";
  public  static  int p=0 ;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_client);
    Intent i = getIntent();

    final int id = i.getIntExtra(MainActivity.id, 0);
    if (id!=0){
      p=id;}





   Button Btn = (Button)findViewById(R.id.bottom);
    Btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i  = new Intent(Client.this,AddProg.class);
        i.putExtra(id1,p);


        startActivity(i);

      }
    });


    dbHelper = new DatabaseHelper(this);
    displayListView(id);

  }
  @Override
  protected void onResume() {
    super.onResume();

      dbHelper = new DatabaseHelper(this);
      displayListView(p);
    }



  private void displayListView(int idc) {
    Cursor cursor = dbHelper.fetchAllProgramsid(idc);

    // The desired columns to be bound
    String[] columns = new String[]{
      "num_p",
      "date_debut",
      "duree",
      "maladie",

    };
    // the XML defined views which the data will be bound to
    int[] to = new int[]{
      R.id.num_p,
      R.id.date_debut,
      R.id.duree,
      R.id.maladie,

    };

    // create the adapter using the cursor pointing to the desired data
    //as well as the layout information
    dataAdapter = new SimpleCursorAdapter(
      this, R.layout.layout_program_info,
      cursor,
      columns,
      to,
      0);

    ListView listView = (ListView) findViewById(R.id.listView1);
    // Assign adapter to ListView
    listView.setAdapter(dataAdapter);
    listView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView listView, View view,
                              int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String ProgramNum =
          cursor.getString(cursor.getColumnIndexOrThrow("num_p"));
       Intent p= new Intent(Client.this, ProgMed.class);
       int progid= Integer.parseInt(ProgramNum);
       p.putExtra(id1,progid);
       startActivity(p);

      }
    });

  }
}
