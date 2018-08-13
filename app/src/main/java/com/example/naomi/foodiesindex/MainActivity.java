package com.example.naomi.foodiesindex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String uEmail;

    private String pUName, pEmail, pPassword;
    private EditText txtEmail, txtPassword;

    String msg = "Android : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    @Override
    public void onClick(View view) {
        //get data from textbox
        pEmail = txtEmail.getText().toString();
        pPassword = txtPassword.getText().toString();

        DBAdaptor db = new DBAdaptor(this);

        switch (view.getId()) {
            case R.id.btnLogin:
                // check for empty data
                if (pEmail.length() == 0) {
                    Toast.makeText(this, "Enter your e-mail address", Toast.LENGTH_SHORT).show();
                } else if (pPassword.length() == 0) {
                    Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
                } else {
                    //validate user
                    db.open();
                    if (db.validateUser(pEmail, pPassword)) {
                        uEmail = pEmail;
                        Intent toHomepage = new Intent(this, Home.class);
                        startActivity(toHomepage);
                    } else {
                        Toast.makeText(this, "Please check that you have entered your info correctly",
                                Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }
                break;
            case R.id.btnRegister:
                Intent toRegister = new Intent(MainActivity.this, Registration.class);
                startActivity(toRegister);
                break;
        }
    }
}

