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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText username;
    private EditText password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.username_register);
        password = (EditText) findViewById(R.id.password_register);
        register = (Button) findViewById(R.id.register_button);

        register.setVisibility(View.VISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(Register.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(username.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    public void registerUser(String email, String password){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("USER REG ---> ", "createUserWithEmail:success");
                            final FirebaseUser activeFirebaseUser = firebaseAuth.getCurrentUser();
                            Toast.makeText(Register.this, "Registered!", Toast.LENGTH_SHORT).show();

                            final User user = new User();
                            if (activeFirebaseUser != null) {
                                user.setID(activeFirebaseUser.getUid());
                            }
                            user.setUsername(activeFirebaseUser.getEmail());

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(Register.this, UserProfileSetup1.class);
                                    intent.putExtra("CurrentFirebaseUser", activeFirebaseUser);
                                    intent.putExtra("CurrentCustomUser", user);
                                    register.setVisibility(View.INVISIBLE);
                                    startActivity(intent);
                                }
                            }, 2000);
                            //updateUI(user);
                        } else {
                            Log.i("USER REG ---> ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

}