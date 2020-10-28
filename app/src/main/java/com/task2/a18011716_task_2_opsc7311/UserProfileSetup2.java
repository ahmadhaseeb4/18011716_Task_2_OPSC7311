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

public class UserProfileSetup2 extends AppCompatActivity {

    private TextView signedInAS;
    private EditText currentHeightET;
    private EditText currentWeightET;
    User currentCustomUser;
    Button next;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_setup2);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        signedInAS = findViewById(R.id.signedin_user_email_textview);
        next = findViewById(R.id.nextProfileButton2);
        currentHeightET = findViewById(R.id.currentheight_profile_setup2);
        currentWeightET = findViewById(R.id.currentweight_profile_setup2);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAS.setText(currentCustomUser.getUsername());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentHeightET.getText().toString().equals("") || currentWeightET.getText().toString().equals("")){
                    Toast.makeText(UserProfileSetup2.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    currentCustomUser.setCurrentHeight(currentHeightET.getText().toString());
                    currentCustomUser.setCurrentWeight(currentWeightET.getText().toString());

                    databaseReference.child("Users").child(currentCustomUser.getID()).child("currentHeight").setValue(currentHeightET.getText().toString());
                    databaseReference.child("Users").child(currentCustomUser.getID()).child("currentWeight").setValue(currentWeightET.getText().toString());

                    Intent intent = new Intent(UserProfileSetup2.this, Dashboard.class);
                    intent.putExtra("CurrentCustomUser", currentCustomUser);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }
        });
    }
}