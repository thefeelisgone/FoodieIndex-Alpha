package com.example.naomi.foodiesindex;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements View.OnClickListener {

    //Popular Restaurants info
    ListView resList;
    int[] images = {R.drawable.lola_cafe, R.drawable.aydc, R.drawable.imkim, R.drawable.coco};
    String[] names = {"Lola's Cafe", "After You Dessert Cafe", "I'm KIM Korean BBQ", "CoCo Ichibanya"};
    String[] cuisines = {"Local Delights", "Dessert", "Korean Cuisine", "Japanese Cuisine"};

    //clicked items
    public static String name, cuisine;
    public static int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnAddRev).setOnClickListener(this);

        //customising the Popular Restaurants ListView
        resList = (ListView) findViewById(R.id.popResList);
        CustomAdaptor customAdaptor = new CustomAdaptor();
        resList.setAdapter(customAdaptor);

        //setting onClick for ListView items
        final ArrayList<String> restaurants = new ArrayList<>();
        resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent resPage = new Intent(Home.this, Restaurant.class);
                startActivity(resPage);

            }
        });
    }

    // Create a drop down menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // Add options for the drop down menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            // When select the "Settings" option
            case R.id.settings:
                // Direct to the Settings page
                startActivity(new Intent(this, Settings.class));
                return true;


            // When select the "About" option
            case R.id.about:
                // Direct to the About page
                startActivity(new Intent(this, About.class));
                return true;


            // When select the "Sign out" option
            case R.id.signout:
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);

                // Set title for the alert dialog
                mBuilder.setTitle(R.string.titleSignout);

                // Set message for the alert dialog
                mBuilder.setMessage(R.string.messageSignout);

                // Create "Sign out" confirm button
                mBuilder.setNegativeButton(R.string.signout, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Direct to login page
                        startActivity(new Intent(Home.this, MainActivity.class));
                    }
                });

                // Create "Cancel" confirm button
                mBuilder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Stay on the same page
                    }
                });

                // Create alert dialog
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();

                return true;
        }
        return false;
    }

    //set info for ListView
    class CustomAdaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.customlayout, null);
            ImageView imageView = view.findViewById(R.id.resImage);
            TextView textViewName = view.findViewById(R.id.resName);
            TextView textViewCuisine = view.findViewById(R.id.cuisineType);

            imageView.setImageResource(images[position]);
            textViewName.setText(names[position]);
            textViewCuisine.setText(cuisines[position]);

            return view;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddRev:
                Intent addReview = new Intent(Home.this, AddReview.class);
                startActivity(addReview);
        }
    }
}
