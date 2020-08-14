package com.example.decider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddingActivity extends AppCompatActivity {
    private Object Parcelable;

    Boolean checker = false;
    Boolean checker1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        /*Variables*/
        final EditText generatorList = findViewById(R.id.listOfItemsTextView);
        final EditText input = findViewById(R.id.inputEditText);
        final ArrayList<String> arrayList = new ArrayList<>();
        Button addButton = findViewById(R.id.addButton);
        Button finishButton = findViewById(R.id.finishButton);
        Button delButton = findViewById(R.id.delButton);

        generatorList.setMovementMethod(new ScrollingMovementMethod());

        SharedPreferences prefs = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String str = prefs.getString("key", "");
        String[] s = str.split(",");
        if (s.length > 0){
            for(int i=0; i < s.length; i++){
                arrayList.add(s[i]);
                generatorList.append(s[i]);
                generatorList.append("\n");
            }
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (! text.isEmpty()) {
                    arrayList.add(text);
                    generatorList.append(text);
                    generatorList.append("\n");
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }

            }
        });

        final ArrayList<String> finalArrayList = arrayList;
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddingActivity.this, MainActivity.class);
                if (finalArrayList.size() == 0){
                    finalArrayList.add("Yes");
                    finalArrayList.add("No");
                }
                i.putExtra("key", finalArrayList);
                setResult(RESULT_OK, i);
                SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
                editor.putString("key", arrayListToString(arrayList));
                editor.commit();
                startActivity(i);
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Deleted", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                deleteSharedPreferences("MyPref");
                int aSize = arrayList.size();
                for (int i = 0; i < aSize; i++){
                    arrayList.remove(0);
                }
                generatorList.getText().clear();
            }
        });

        input.setImeActionLabel("Add", KeyEvent.KEYCODE_ENTER);
        input.setImeOptions(6);
    }

    /**
     * https://www.tutorialspoint.com/How-to-create-a-string-from-a-Java-ArrayList
     * @param arrayList
     */
    public String arrayListToString(ArrayList<String> arrayList){
        StringBuffer sb = new StringBuffer();

        for( String s: arrayList){
            sb.append(s);
            sb.append(",");
        }
        Log.d("TAG", sb.toString());
        return sb.toString();
    }


}