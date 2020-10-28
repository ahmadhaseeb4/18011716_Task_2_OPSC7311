package com.task2.a18011716_task_2_opsc7311;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LogWeight extends AppCompatActivity {

    private TextView signedInAS;
    EditText weight;
    User currentCustomUser;
    Button next;
    Button log;
    DatabaseReference databaseReference;
    long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_weight);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAS = findViewById(R.id.signedin_user_email_textview);
        next = findViewById(R.id.nextProfileButton2);
        log = findViewById(R.id.logWeight);
        weight = findViewById(R.id.logWeitghET);
        signedInAS.setText(currentCustomUser.getUsername());
        size = 0;

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Users");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                size = dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                databaseReference.child("Users").child(currentCustomUser.getID()).child("deltaWeight").child(Long.toString(size)).setValue("Your weight was " + weight.getText().toString() + " on the " + timeStamp);
            }
        });
    }
}