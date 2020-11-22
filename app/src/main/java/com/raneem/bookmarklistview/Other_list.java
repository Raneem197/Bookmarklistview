package com.raneem.bookmarklistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Other_list extends AppCompatActivity {

    ListView listview2;
    StorageReference storageRef;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> list2=new ArrayList<>();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_list);
        listview2 = (ListView)findViewById(R.id.listview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ViewAllFailes();




    }
    public void ViewAllFailes () {
        if(user!=null) {
            final String userID = user.getUid();

            mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("booked");
            mDatabase.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Log.d("Before check DB",""+snapshot.toString());
                    String value = snapshot.getValue(String.class);
                    list2.add(value);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(Other_list.this, android.R.layout.simple_list_item_1, list2);
                    listview2.setAdapter(arrayAdapter);
                    Log.d("check DB", "" + snapshot.toString());


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }




            }}
