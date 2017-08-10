package com.hilfritz.autocompletehistorytextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hilfritz.autocompletehistorytextview.view.AutoCompleteHistoryTextView;

public class MainActivity extends AppCompatActivity {

    Button saveBtn;
    Button resetBtn;
    AutoCompleteHistoryTextView name, age, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button) findViewById(R.id.save_btn);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        name = (AutoCompleteHistoryTextView) findViewById(R.id.name);
        age = (AutoCompleteHistoryTextView) findViewById(R.id.age);
        email = (AutoCompleteHistoryTextView) findViewById(R.id.email);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.isEmptyInput()){
                    toast("Please provide name.");
                    return;
                }
                if (email.isEmptyInput()){
                    toast("Please provide email.");
                    return;
                }
                if (age.isEmptyInput()){
                    toast("Please provide age.");
                    return;
                }
                name.saveToHistory(name.getText().toString());
                email.saveToHistory(email.getText().toString());
                age.saveToHistory(age.getText().toString());
                toast("Saved!");
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.reset();
                email.reset();
                age.reset();
                toast("Reset done!");
            }
        });
    }

    public void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
