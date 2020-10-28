package com.task2.a18011716_task_2_opsc7311;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private TextView signedInAS;
    private TextView firstName;
    private TextView lastName;
    User currentCustomUser;
    Button next;
    Button goal;
    Button log;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signedInAS = findViewById(R.id.signedin_user_email_textview);
        next = findViewById(R.id.nextProfileButton2);
        firstName = findViewById(R.id.firstname_dashboard);
        lastName = findViewById(R.id.lastname_dashboard);
        goal = findViewById(R.id.goalButton);
        log = findViewById(R.id.logButton);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        //char[] first = currentCustomUser.getFirstName().toCharArray();

        signedInAS.setText(currentCustomUser.getUsername());
        firstName.setText(currentCustomUser.getFirstName().charAt(0) + ".");
        lastName.setText(currentCustomUser.getLastName());

        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Gaols.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Log.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, currentCustomUser.getFirstName().charAt(0), Toast.LENGTH_SHORT).show();
            }
        });
    }
}