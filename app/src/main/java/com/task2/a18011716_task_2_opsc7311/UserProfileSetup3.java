package com.task2.a18011716_task_2_opsc7311;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileSetup3 extends AppCompatActivity {

    private TextView signedInAS;
    private RadioGroup rg;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    User currentCustomUser;
    Button next;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_setup3);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signedInAS = findViewById(R.id.signedin_user_email_textview);
        next = findViewById(R.id.nextProfileButton3);
        radioButton1 = findViewById(R.id.metricRB);
        radioButton2 = findViewById(R.id.imperialRB);
        rg = findViewById(R.id.mainRG);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAS.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rg.getCheckedRadioButtonId() == radioButton1.getId()){
                    currentCustomUser.setSystem("Metric System");
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("system").setValue("Metric System");

                    Intent intent = new Intent(UserProfileSetup3.this, UserProfileSetup2.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);
                }
                else if (rg.getCheckedRadioButtonId() == radioButton2.getId()){
                    currentCustomUser.setSystem("Imperial System");
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("system").setValue("Imperial System");

                    Intent intent = new Intent(UserProfileSetup3.this, UserProfileSetup2.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    startActivity(intent);
                }
            }
        });
    }
}