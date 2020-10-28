package com.task2.a18011716_task_2_opsc7311;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileSetup1 extends AppCompatActivity {

    Button next;
    TextView signedInAs;
    EditText firstNameET;
    EditText lastNameET;
    User currentCustomUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_setup1);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        next = findViewById(R.id.nextProfileButton1);
        signedInAs = findViewById(R.id.signedin_user_email_textview);
        firstNameET = findViewById(R.id.firstname_profile_setup1);
        lastNameET = findViewById(R.id.lastname_profile_setup1);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAs.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstNameET.getText().toString().equals("") || lastNameET.getText().toString().equals("")){
                    Toast.makeText(UserProfileSetup1.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    currentCustomUser.setFirstName(firstNameET.getText().toString());
                    currentCustomUser.setLastName(lastNameET.getText().toString());

                    databaseReference.child("Users").child(currentCustomUser.getID()).setValue(currentCustomUser);

                    Intent intent = new Intent(UserProfileSetup1.this, UserProfileSetup3.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);

                }
            }
        });
    }
}