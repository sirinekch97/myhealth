package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  DatabaseHelper db;
  public final static  String id = "id";
  Button btnSignIn,btnSignUp;
  EditText editTextUserName,editTextPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    db = new DatabaseHelper(this);


// Get The Refference Of Buttons
    btnSignIn=(Button)findViewById(R.id.buttonSignIN);
    btnSignUp=(Button)findViewById(R.id.buttonSignUP);
    editTextUserName=(EditText)findViewById(R.id.UserNameToLogin);
    editTextPassword=(EditText)findViewById(R.id.PasswordToLogin);
// Set OnClick Listener on SignUp button
    btnSignUp.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {


/// Create Intent for SignUpActivity abd Start The Activity
        Intent intentSignUP=new Intent(getApplicationContext(),Signup.class);
        startActivity(intentSignUP);
      }
    });


// Set On ClickListener
      btnSignIn.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
// get The User name and Password
          String userName=editTextUserName.getText().toString();
          String password=editTextPassword.getText().toString();
          if ((password.equals("admin"))&&(userName.equals("admin"))){
            Toast.makeText(MainActivity.this, "Bienvenu admin", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this,Admin.class);
            startActivity(i);
          }
          else
// fetch the Password form database for respective user name
          {
            String storedPassword=db.getSinlgeEntry(userName);
            int  storedID=db.getidexEntry(userName);
// check if the Stored password matches with Password entered by user
            if(password.equals(storedPassword))
            {
              Toast.makeText(MainActivity.this, "Bienvenu ! ", Toast.LENGTH_LONG).show();


              Intent j= new Intent(MainActivity.this,Client.class);
              j.putExtra(id,storedID);
              startActivity(j);
            }
            else
            {
              Toast.makeText(MainActivity.this, "Nom ou mot de passe incorrecte", Toast.LENGTH_LONG).show();
            }}
        }
      });

    }


  }

