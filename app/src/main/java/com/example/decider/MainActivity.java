package com.example.decider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Variables*/
        Button rollButton = findViewById(R.id.rollButton);
        final TextView resultTextView =  findViewById(R.id.resultTextView);
        final FloatingActionButton fab = findViewById(R.id.AddFAB);
        final ArrayList<String>[] arrayList = new ArrayList[]{new ArrayList<String>()};


        /** For the chosen button**/
        rollButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                /**Getting the data from the other activity**/
                Bundle bundle = getIntent().getExtras();
                arrayList[0] = bundle.getStringArrayList("key");


                int max = arrayList[0].size();
                Log.d("sad", arrayList[0].get(0));
                Random r = new Random();
                int rand = r.nextInt(max);

                resultTextView.setText(arrayList[0].get(rand));

            }
        });

        /**For the FAB button
         * https://stackoverflow.com/questions/31624935/floatingactionbutton-expand-into-a-new-activity
         * **/
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){


                final Intent intent = new Intent(MainActivity.this, AddingActivity.class);
                startActivity(intent);
            }

        });

    }



}