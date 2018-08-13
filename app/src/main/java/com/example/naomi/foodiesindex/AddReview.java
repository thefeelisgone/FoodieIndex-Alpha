package com.example.naomi.foodiesindex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class AddReview extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        //Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btnAddRev).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        //Ends the activity
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPost:
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(AddReview.this);

                // Set title for the alert dialog
                mBuilder.setTitle(R.string.titlePost);

                // Set message for the alert dialog
                mBuilder.setMessage(R.string.messagePost);

                // Create "OK" confirm button
                mBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Direct to home page
                        startActivity(new Intent(AddReview.this, Home.class));
                    }
                });

                // Create alert dialog
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();
        }
    }
}
