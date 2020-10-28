package com.task2.a18011716_task_2_opsc7311;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Log extends AppCompatActivity {

    Button next;
    TextView signedInAs;
    User currentCustomUser;
    Button weight;
    Button food;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        next = findViewById(R.id.nextProfileButton1);
        signedInAs = findViewById(R.id.signedin_user_email_textview);
        weight = findViewById(R.id.logWeight);
        food = findViewById(R.id.logFood);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAs.setText(currentCustomUser.getUsername());

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log.this, LogWeight.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}