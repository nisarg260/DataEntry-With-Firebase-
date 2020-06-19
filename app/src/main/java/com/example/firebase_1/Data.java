package com.example.firebase_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Data extends AppCompatActivity {
    //TextView display;
    DatabaseReference reff;
    //StringBuilder builder =new StringBuilder();
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter <String> adapter;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


       // display = (TextView)findViewById(R.id.display);
        user = new User();
        listView = (ListView)findViewById(R.id.listview);

        reff = FirebaseDatabase.getInstance().getReference().child("Users");
        list = new ArrayList<>();

        // 1st this for this activity
        // 2nd layout which we use
        // 3rd textview id in layout
        // list make in upper


        adapter = new ArrayAdapter<String>(this, R.layout.userinfo, R.id.user_info, list);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    user = dataSnapshot1.getValue(User.class);
                    list.add("Name: " + user.getName().toString() + "\n" + "Email: " + user.getEmail().toString() + "\n" + "Username: " + user.getUsername().toString() + "\n" + "Password: " + user.getPassword().toString() + "\n");

                    /*String name = dataSnapshot1.child("Name").getValue().toString();
                    String email = dataSnapshot1.child("Email").getValue().toString();
                    String username = dataSnapshot1.child("Username").getValue().toString();

                    builder.append("Name: " + name + "\nUsername: "+ username + "\nEmail: " + email + "\n\n");

                    display.setText(builder.toString());*/

                }

                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}