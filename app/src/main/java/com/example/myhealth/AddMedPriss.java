package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMedPriss extends AppCompatActivity {
  EditText numpr, desc, dt,hr,qutt;
  Button saveB;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_med_priss);
    Intent i = getIntent();
    final int id = i.getIntExtra(ProgMedPrise.id1, 0);
    numpr = (EditText)findViewById(R.id.npr);
    desc = (EditText)findViewById(R.id.descp);
    dt = (EditText)findViewById(R.id.datte);
    hr = (EditText)findViewById(R.id.hr);
    qutt=(EditText)findViewById(R.id.qtte);

    saveB = (Button)findViewById(R.id.Save);
    saveB.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String nump = numpr.getText().toString()+"\n";
        String descr = desc.getText().toString();
        String date = dt.getText().toString();
        String her = hr.getText().toString();
        String qt= qutt.getText().toString();


        DatabaseHelper dbHandler = new DatabaseHelper(AddMedPriss.this);
        dbHandler.insertpriseDetails(nump,descr,date,her,qt,id);
        Intent intent = new Intent(AddMedPriss.this, ProgMedPrise.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
      }
    });
  }
}
