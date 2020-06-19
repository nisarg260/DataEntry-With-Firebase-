package com.example.firebase_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText name, username, password, email;
    Button register, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.pwd);
        email = (EditText)findViewById(R.id.email);
        register = (Button)findViewById(R.id.register);
        data = (Button)findViewById(R.id.data);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String namedb = name.getText().toString();
                final String pwddb = password.getText().toString();
                final String emaildb = email.getText().toString();
                final String usernamedb = username.getText().toString();


                if (TextUtils.isEmpty(namedb)) {
                    name.requestFocus();
                    name.setError("Fill This Field");
                } else if (TextUtils.isEmpty(usernamedb)) {
                    username.requestFocus();
                    username.setError("Fill This Field");
                } else if (TextUtils.isEmpty(pwddb)){
                    password.requestFocus();
                    password.setError("Fill This Field");
                } else if (TextUtils.isEmpty(emaildb)) {
                    email.requestFocus();
                    email.setError("Fill This Field");
                } else {


                    HashMap<String, Object> map = new HashMap<>();
                    map.put("Name", namedb);
                    map.put("Password", pwddb);
                    map.put("Email", emaildb);
                    map.put("Username", usernamedb);

                    FirebaseDatabase.getInstance().getReference().child("Users").child(usernamedb).setValue(map)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        name.setText(null);
                                        password.setText(null);
                                        email.setText(null);
                                        username.setText(null);

                                        Toast.makeText(MainActivity.this, ":) You Are Successfully Registered ", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(MainActivity.this, ":( You Are not Eligible To Register ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                }
            }
        });


        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Data.class);
                startActivity(i);
            }
        });

    }
}