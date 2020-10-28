package com.task2.a18011716_task_2_opsc7311;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView registerTextView;
    Button login;
    private FirebaseAuth firebaseAuth;
    private EditText username, password;
    private DatabaseReference databaseReference;
    User activeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerTextView = findViewById(R.id.signedin_user_email_textview);
        login = findViewById(R.id.login_button);
        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        login.setVisibility(View.VISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

        showRegisterActivity(registerTextView);

    }

    public void loginUser(String username, String password){
        Toast.makeText(Login.this, "Please wait...", Toast.LENGTH_SHORT).show();
        firebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            Log.i("USER LOGIN ---> ", "signInWithCustomToken:success");
                            final FirebaseUser authUser = firebaseAuth.getCurrentUser();

                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    activeUser = snapshot.child(authUser.getUid()).getValue(User.class);
                                    Log.i("DATA ------> ", activeUser.getWeightGoal() + " / " + activeUser.getCalorieIntakeGoal());
                                    Intent intent = new Intent(Login.this, Dashboard.class);
                                    intent.putExtra("CurrentCustomUser", activeUser);
                                    login.setVisibility(View.INVISIBLE);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            Log.i("USER LOGIN ---> ", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void showRegisterActivity(TextView registerTextView){

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}