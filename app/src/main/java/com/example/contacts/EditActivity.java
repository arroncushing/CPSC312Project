package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button saveContactButton = (Button) findViewById(R.id.saveContactButton);
        final EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        final EditText phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        final EditText eMailEditText = (EditText) findViewById(R.id.eMailEditText);

        if(getIntent() != null){
            nameEditText.setText(getIntent().getStringExtra("name"));
            phoneNumberEditText.setText(getIntent().getStringExtra("phoneNumber"));
            eMailEditText.setText(getIntent().getStringExtra("emailAddress"));
        }

        // exits this Activity and returns the information entered/edited by the user to MainActivity
        saveContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameEditText.getText().toString().equals("") || phoneNumberEditText.getText().toString().equals("") || eMailEditText.getText().toString().equals("")){
                    Toast.makeText(EditActivity.this, "Do not leave any empty fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    getIntent().putExtra("name", nameEditText.getText().toString());
                    getIntent().putExtra("phoneNumber", phoneNumberEditText.getText().toString());
                    getIntent().putExtra("emailAddress", eMailEditText.getText().toString());
                    finish();
                }
            }
        });
    }
}
