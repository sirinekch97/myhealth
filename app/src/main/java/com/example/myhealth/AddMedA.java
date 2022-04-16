package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMedA extends AppCompatActivity {
  EditText refmed, datedebcons, duree;
  Button saveBtn;
  Intent intent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_med2);
    Intent i = getIntent();
    final int id = i.getIntExtra(ProgGestP.id1, 0);
    refmed = (EditText)findViewById(R.id.medrefa);
    datedebcons = (EditText)findViewById(R.id.dateconsa);
    duree = (EditText)findViewById(R.id.dureetxta);


    saveBtn = (Button)findViewById(R.id.btnSavea);
    saveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String medr = refmed.getText().toString()+"\n";
        String dbcos = datedebcons.getText().toString();
        String dr = duree.getText().toString();

        DatabaseHelper dbHandler = new DatabaseHelper(AddMedA.this);
        dbHandler.insertmedDetails(medr,dbcos,dr,id);
        intent = new Intent(AddMedA.this,ProgGestP.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();

      }
    });
  }
}
