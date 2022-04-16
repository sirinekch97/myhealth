package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProg extends AppCompatActivity {
  EditText nump, datedeb, duree,maladie;
  Button saveBtn;
  Intent intent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_prog);


    Intent i = getIntent();
    final int id = i.getIntExtra(Client.id1, 0);
    nump = (EditText)findViewById(R.id.txtNumP);
    datedeb = (EditText)findViewById(R.id.txtDatedeb);
    duree = (EditText)findViewById(R.id.txtDuree);
    maladie = (EditText)findViewById(R.id.txtmaladie);



    saveBtn = (Button)findViewById(R.id.btnSave);
    saveBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String numprog = nump.getText().toString()+"\n";
        String datedebut = datedeb.getText().toString();
        String dure = duree.getText().toString();
        String malad = maladie.getText().toString();


        DatabaseHelper dbHandler = new DatabaseHelper(AddProg.this);
        dbHandler.insertprogDetails(numprog,datedebut,dure,malad,id);
        intent = new Intent(AddProg.this,Client.class);

        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Details Inserted Successfully",Toast.LENGTH_SHORT).show();
      }
    });



  }
}
