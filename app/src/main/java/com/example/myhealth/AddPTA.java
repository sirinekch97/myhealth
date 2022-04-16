package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPTA extends AppCompatActivity {
  EditText numt, deg;
  Button savetb;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_p_t2);
    Intent i = getIntent();
    final int id = i.getIntExtra(ProgTempA.id1, 0);
    numt = (EditText)findViewById(R.id.pta);
    deg = (EditText)findViewById(R.id.dga);
    savetb = (Button)findViewById(R.id.Savetpa);

    savetb.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nump = numt.getText().toString()+"\n";
        String descr = deg.getText().toString();



        DatabaseHelper dbHandler = new DatabaseHelper(AddPTA.this);
        dbHandler.inserttempDetails(nump,descr,id);
        Intent intent = new Intent(AddPTA.this, ProgTempA.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
      }
    });
  }
}
