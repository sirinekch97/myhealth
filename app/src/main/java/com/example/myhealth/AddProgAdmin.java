package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProgAdmin extends AppCompatActivity {
  EditText nump, datedeb, duree,maladie,patientnum;
  Button saveBtn;
  Intent intent;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_prog_admin);

    nump = (EditText)findViewById(R.id.NumP);
    datedeb = (EditText)findViewById(R.id.Datedeb);
    duree = (EditText)findViewById(R.id.Duree);
    maladie = (EditText)findViewById(R.id.maladie);
    patientnum=(EditText)findViewById(R.id.patientid1);
    saveBtn = (Button)findViewById(R.id.btnSave1);
    saveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String numprog = nump.getText().toString()+"\n";
        String datedebut = datedeb.getText().toString();
        String dure = duree.getText().toString();
        String malad = maladie.getText().toString();
        String pat= patientnum.getText().toString();
        int p =Integer.parseInt(pat);

        DatabaseHelper dbHandler = new DatabaseHelper(AddProgAdmin.this);
        dbHandler.insertprogDetails(numprog,datedebut,dure,malad,p);
        intent = new Intent(AddProgAdmin.this,Admin.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
      }
    });
  }
}
