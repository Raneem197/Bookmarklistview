package com.raneem.bookmarklistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ListView listview;
    StorageReference storageRef;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    List<Slides> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.listview);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storageRef= FirebaseStorage.getInstance().getReference();

        //chekbook();
         // ReadFairebaseFailes();
        ViewAllFailes();


    }
    public void ViewAllFailes () {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("JavaOneSlides");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Slides ss = ds.getValue(Slides.class);
                    ss.setId(ds.getKey());
                    list.add(ss);



                }


                Myadapter adapter = new Myadapter(getApplicationContext(), list);
                listview.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }});
    }
    private void ReadFairebaseFailes() {
        Slides s1=new Slides("JavaBasics","https://firebasestorage.googleapis.com/v0/b/bookmarklistview.appspot.com/o/Lec_4%20-%20Conditions(1).pdf?alt=media&token=59e63c0a-e822-4e05-bdf9-02df6eb6419e"
                ,R.drawable.notbookkmark,false);
        Slides s2=new Slides("methods ","https://firebasestorage.googleapis.com/v0/b/bookmarklistview.appspot.com/o/Lec_5%266%20-%20Loops-converted.pdf?alt=media&token=c8ec774d-fe82-40b0-93ae-faedb4ad41e1",
                R.drawable.notbookkmark,false);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s1);
        firebaseDatabase.getReference("JavaOneSlides").push().setValue(s2);

    }

}