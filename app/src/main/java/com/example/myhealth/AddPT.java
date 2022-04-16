package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPT extends AppCompatActivity {
  EditText numt, deg;
  Button savetb;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_p_t);
    Intent i = getIntent();
    final int id = i.getIntExtra(ProgTemp.id1, 0);
    numt = (EditText)findViewById(R.id.pt);
    deg = (EditText)findViewById(R.id.dg);
   savetb = (Button)findViewById(R.id.Savetp);

    savetb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nump = numt.getText().toString()+"\n";
        String descr = deg.getText().toString();



        DatabaseHelper dbHandler = new DatabaseHelper(AddPT.this);
        dbHandler.inserttempDetails(nump,descr,id);
        Intent intent = new Intent(AddPT.this, ProgTemp.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
      }
    });
  }
}
