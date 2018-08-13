package com.example.naomi.foodiesindex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    private String pUName, pEmail, pPassword;
    private EditText editUName, editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editUName = (EditText)findViewById(R.id.editUName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editPassword);

        findViewById(R.id.btnRegistered).setOnClickListener(this);

        // Add back button on top of page
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        //get data from textbox
        pUName = editUName.getText().toString();
        pEmail = editEmail.getText().toString();
        pPassword = editPassword.getText().toString();

        DBAdaptor db = new DBAdaptor(this);

        switch (view.getId()) {
            case R.id.btnRegistered:
                // check for empty data
                if (pUName.length() == 0) {
                    Toast.makeText(this,"Enter your name", Toast.LENGTH_SHORT).show();
                } else if (pEmail.length() ==0) {
                    Toast.makeText(this, "Enter your e-mail address", Toast.LENGTH_SHORT).show();
                } else if (pPassword.length() == 0) {
                    Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
                } else if (db.checkIfUserExists(pUName, pEmail, pPassword)) {
                    Toast.makeText(this,
                            "You already have an account. Please sign in.", Toast.LENGTH_SHORT).show();
                } else {
                    //add a user
                    db.open();
                    db.insertUser(pUName, pEmail, pPassword);
                    db.close();

                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Registration.this);

                    // Set title for the alert dialog
                    mBuilder.setTitle(R.string.titleRegist);

                    // Set message for the alert dialog
                    mBuilder.setMessage(R.string.messageRegist);

                    // Create "OK" confirm button
                    mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Direct to login page
                            startActivity(new Intent(Registration.this, MainActivity.class));
                        }
                    });

                    // Create alert dialog
                    AlertDialog alertDialog = mBuilder.create();
                    alertDialog.show();
                }
                break;
        }
    }
    
    // Direct back to the previous (Login) page
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        //Ends the activity
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
