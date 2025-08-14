package com.hilfritz.autocompletehistorytextview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlinx.coroutines.*;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hilfritz.autocompletehistorytextview.view.AutoCompleteHistoryTextView;

public class MainActivity extends AppCompatActivity {

    Button saveBtn;
    Button resetBtn;
    AutoCompleteHistoryTextView name, age, email;
    ConstraintLayout root;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveBtn = (Button) findViewById(R.id.save_btn);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        name = (AutoCompleteHistoryTextView) findViewById(R.id.name);
        age = (AutoCompleteHistoryTextView) findViewById(R.id.age);
        email = (AutoCompleteHistoryTextView) findViewById(R.id.email);
        root = findViewById(R.id.constraint_layout);
        // Replace MyCustomView.class with your actual custom view class
        root.setOnClickListener(view -> {
            clearFocus();
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "saveBtn: onClick: ");
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
                Log.d(TAG, "saveBtn: onClick: Field inputs okay");
                name.saveToHistory(name.getText().toString());
                email.saveToHistory(email.getText().toString());
                age.saveToHistory(age.getText().toString());
                Log.d(TAG, "saveBtn: onClick: Field inputs saved.");
                clearFocus();

                toast("Saved!");
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "resetBtn: onClick: ");
                clearFocus();
                clearFieldInputs();
                name.reset();
                email.reset();
                age.reset();
                toast("Reset done!");
            }
        });

    }

    private void clearFieldInputs() {
        root.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: delay: clearFieldInputs: ");
                name.setText("");
                email.setText("");
                age.setText("");
            }
        }, 1500);

    }

    private void clearFocus() {
        FocusUtils.clearFocusFromCustomViews(
                root,
                AutoCompleteHistoryTextView.class,
                /* clearAnyFocusedView = */ true,
                /* hideKeyboard = */ true
        );
    }

    public void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
