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

public class Gaols extends AppCompatActivity {

    Button next;
    TextView signedInAs;
    EditText firstNameET;
    EditText lastNameET;
    User currentCustomUser;
    DatabaseReference databaseReference;
    Button goalsDone;
    EditText tCalories;
    EditText tWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaols);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        next = findViewById(R.id.nextProfileButton1);
        signedInAs = findViewById(R.id.signedin_user_email_textview);
        firstNameET = findViewById(R.id.firstname_profile_setup1);
        lastNameET = findViewById(R.id.lastname_profile_setup1);
        goalsDone = findViewById(R.id.goals_done_button);

        tCalories = findViewById(R.id.target_calories);
        tWeight = findViewById(R.id.target_weight);

        currentCustomUser = (User) getIntent().getSerializableExtra("CurrentCustomUser");

        signedInAs.setText(currentCustomUser.getUsername());

        if (currentCustomUser.getWeightGoal() == null || currentCustomUser.getWeightGoal().equals("")){ }
        else{
            tWeight.setText(currentCustomUser.getWeightGoal());
        }

        if (currentCustomUser.getCalorieIntakeGoal() == null || currentCustomUser.getCalorieIntakeGoal().equals("")){ }
        else{
            tCalories.setText(currentCustomUser.getCalorieIntakeGoal());
        }

        goalsDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentCustomUser.setWeightGoal(tWeight.getText().toString());
                currentCustomUser.setCalorieIntakeGoal(tCalories.getText().toString());

                databaseReference = FirebaseDatabase.getInstance().getReference("Users");

                databaseReference.child(currentCustomUser.getID()).child("weightGoal").setValue(tWeight.getText().toString());
                databaseReference.child(currentCustomUser.getID()).child("calorieIntakeGoal").setValue(tCalories.getText().toString());

                Intent intent = new Intent(Gaols.this, Dashboard.class);
                intent.putExtra("CurrentCustomUser", currentCustomUser);
                startActivity(intent);
            }
        });

    }
}