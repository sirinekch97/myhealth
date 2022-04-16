package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMed extends AppCompatActivity {
  EditText refmed, datedebcons, duree;
  Button saveBtn;
  Intent intent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_med);
    Intent i = getIntent();
    final int id = i.getIntExtra(ProgMed.id1, 0);
    refmed = (EditText)findViewById(R.id.medref);
    datedebcons = (EditText)findViewById(R.id.datecons);
    duree = (EditText)findViewById(R.id.dureetxt);


    saveBtn = (Button)findViewById(R.id.btnSave);
    saveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String medr = refmed.getText().toString()+"\n";
        String dbcos = datedebcons.getText().toString();
        String dr = duree.getText().toString();

        DatabaseHelper dbHandler = new DatabaseHelper(AddMed.this);
        dbHandler.insertmedDetails(medr,dbcos,dr,id);
        intent = new Intent(AddMed.this,ProgMed.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();

  }
});
  }}
