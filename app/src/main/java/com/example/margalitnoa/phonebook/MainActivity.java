package com.example.margalitnoa.phonebook;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String NAME_KEY = "Name";
    private static final String MAJOR_KEY = "Major";
    private static final String INTEREST_KEY = "Interests";
    private static final String EMAIL_KEY = "Email";
    FirebaseFirestore db;
    TextView textDisplay;
    TextView message;
    EditText name, major, interests, email;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        textDisplay = findViewById(R.id.textDisplay);
        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewContact();
            }
        });

    }

    private void addNewContact(){
        save = findViewById(R.id.save);
        name = findViewById(R.id.name);
        major = findViewById(R.id.major);
        interests = findViewById(R.id.interests);
        email = findViewById(R.id.email);
        String mName = name.getText().toString();
        String mMajor = major.getText().toString();
        String mInterests = interests.getText().toString();
        String mEmail = email.getText().toString();
        Map<String, Object>newContact = new HashMap<>();
        newContact.put(NAME_KEY, mName);
        newContact.put(MAJOR_KEY, mMajor);
        newContact.put(INTEREST_KEY, mInterests);
        newContact.put(EMAIL_KEY, mEmail);
        db.collection("PhoneBook").document("Contacts").set(newContact)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Information Submitted",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "ERROR" +e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }
}