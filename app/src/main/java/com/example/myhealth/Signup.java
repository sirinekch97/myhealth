package com.example.myhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
  EditText editTextUserName,editTextPassword,editTextConfirmPassword;
  Button btnCreateAccount;
  DatabaseHelper db;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    db = new DatabaseHelper(this);



// Get Refferences of Views
  editTextUserName=(EditText)findViewById(R.id.editTextUserName);
  editTextPassword=(EditText)findViewById(R.id.editTextPassword);
  editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);

  btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

    public void onClick(View v) {


      String userName=editTextUserName.getText().toString();
      String password=editTextPassword.getText().toString();
      String confirmPassword=editTextConfirmPassword.getText().toString();

// check if any of the fields are vaccant
      if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
      {
        Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
        return;
      }
// check if both password matches
      if(!password.equals(confirmPassword))
      {
        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        return;
      }
      else
      {
// Save the Data in Database
        db.insertEntry(userName, password);
        Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
      }
    }
  });
}

}
