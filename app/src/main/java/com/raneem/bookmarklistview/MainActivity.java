package com.raneem.bookmarklistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    ListView listview;
    StorageReference storageRef;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    //  Set<Slides> list=new HashSet<>();
    List<Slides> list = new ArrayList<>();
    final List<String> booked = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference();
        Button bookmark_button = (Button)
                findViewById(R.id.button2);
        bookmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Other_list.class);
                startActivity(intent);

            }
        });

        //chekbook();
        //  ReadFairebaseFailes();
        ViewAllFailes();


    }

    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public void ViewAllFailes() {
        String userID = null;
        if (user != null) {
            userID = user.getUid();
        }

       /* final Slides s =new Slides();

        final String finalUserID = userID;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("booked");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("users").child(finalUserID).child("booked").exists()){
                    for (DataSnapshot ds : snapshot.getChildren()){
                        booked.add(ds.getValue(String.class));
                        Log.d("bookedValue", ds.getValue(String.class));
                    }
                    s.setIsbooked(true);



                }
                Log.d("Isbooked",s.isIsbooked()+"");


                mDatabase = FirebaseDatabase.getInstance().getReference().child("JavaOneSlides");
                mDatabase.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for (DataSnapshot ds : snapshot.getChildren()) {
                       Slides ss = ds.getValue(Slides.class);
                       //ss.setId(ds.getKey());

                       //Log.d("bookedList", booked.get(0));

                       list.add(ss);

                   }
                   Myadapter adapter = new Myadapter(getApplicationContext(), list);
                   listview.setAdapter(adapter);

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        final List<String> booked = new ArrayList<String>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("booked");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    booked.add(ds.getValue(String.class));
                    Log.d("bookedValue", ds.getValue(String.class));
                }
                mDatabase = FirebaseDatabase.getInstance().getReference().child("JavaOneSlides");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Slides ss = new Slides();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ss = ds.getValue(Slides.class);
                            //ss.setId(ds.getKey());

                            //Log.d("bookedList", booked.get(0));
                          /*  if(booked.indexOf(ss.getTitle())>-1) {
                                ss.setIsbooked(true);
                            }*/
                            //   Log.d("Isbooked",ss.isIsbooked()+"");
                            //list.add(ss);

                        }
                        Log.d("Isbooked", ss.isIsbooked() + "");

                        list.add(ss);

                        if (booked.indexOf(ss.getTitle()) > -1) {
                            ss.setIsbooked(true);
                        }
                        Log.d("Isbooked", ss.isIsbooked() + "");

                       /* List<Slides> temp=new ArrayList<>();
                        for (Slides slides:list){
                            temp.add(slides);
                        }*/
                        Myadapter adapter = new Myadapter(getApplicationContext(), list);
                        listview.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void ReadFairebaseFailes() {
        Slides s1 = new Slides("JavaBasics", "https://firebasestorage.googleapis.com/v0/b/bookmarklistview.appspot.com/o/Lec_4%20-%20Conditions(1).pdf?alt=media&token=59e63c0a-e822-4e05-bdf9-02df6eb6419e"
                , R.drawable.notbookkmark);
        Slides s2 = new Slides("methods ", "https://firebasestorage.googleapis.com/v0/b/bookmarklistview.appspot.com/o/Lec_5%266%20-%20Loops-converted.pdf?alt=media&token=c8ec774d-fe82-40b0-93ae-faedb4ad41e1",
                R.drawable.notbookkmark);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s1);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s2);

    }

}