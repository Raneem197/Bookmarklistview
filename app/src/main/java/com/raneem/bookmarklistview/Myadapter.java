package com.raneem.bookmarklistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends BaseAdapter {  private Context context; //context
    DatabaseReference mDatabase;

    FirebaseDatabase firebaseDatabase;

    List<Slides> list;
    List<String> bookmarklist=new ArrayList<String>();
    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String sss;

    /*
    public MyAdapter( List<String> bookmarklist){

        this.bookmarklist=bookmarklist;
    }*/
    public Myadapter(Context context,  List<Slides> list) {
        this.context = context;
        this.list = list;


    }


    @Override
    public int getCount() {
        return list.size(); //returns total of items in the list;
    }

    @Override
    public Object getItem(int position) {

        return list.get(position); //returns list item at the specified position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row

        final String lectuer=list.get(position).getTitle();









        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.customlist, parent, false); }
        // get current item to be displayed
        //PDFs ss = (PDFs) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.titleview);

     /*   Button bookmark_button=(Button)
                convertView.findViewById(R.id.bookmark_button);*/

        final ImageView imageView = (ImageView)
                convertView.findViewById(R.id.imgview12);

        textViewItemName.setText(list.get(position).getTitle());
        imageView.setImageResource(list.get(position).getImg());
        if(list.get(position).isIsbooked()){
            imageView.setImageResource(R.drawable.bookmarkk);


        }else {
            imageView.setImageResource(R.drawable.notbookkmark);



        }

      //  textViewItemName.setText(list.get(position).getTitle());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (list.get(position).isIsbooked()) {
                    list.get(position).setIsbooked(false);

              if(position>=0) {
         bookmarklist.remove(position);
        notifyDataSetChanged();


} else {
    bookmarklist.remove(position-1);
    notifyDataSetChanged();
}


                }else {
                    list.get(position).setIsbooked(true);
                    bookmarklist.add(list.get(position).getTitle());
                    notifyDataSetChanged();



                }

                  //  final String userID = user.getUid();
                    //mDatabase.child("users").child(userID).child("booked").setValue(lectuer);
              /* list.get(position).setIsbooked(true);

                //if(list.get(position).isIsbooked()){
                  //  bookmarklist.remove(position);
               // }else {
                   bookmarklist.add(list.get(position).getTitle());
                notifyDataSetChanged();*/


                // }


              //  bookmarklist.add(list.get(position).getTitle());


                   /*mDatabase = FirebaseDatabase.getInstance().getReference("JavaOneSlides");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   for(DataSnapshot ds:snapshot.getChildren())
                                      sss=(ds.getKey());
                                bookmarklist.add(sss);
                                // String lectuerid=dataSnapshot.getKey();

                                // String lectuerid=dataSnapshot.getKey();

                           // PDF s=new PDF(lectuer);
                            // mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID);
                         // mDatabase.child("users").child(userID).child("booked").setValue(bookmarklist);

                           // mDatabase.child("booked").push().setValue(s);
                            Toast.makeText(context.getApplicationContext(), "You've booked in successfully", Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



*/

          if(user!=null){
    final String userID = user.getUid();



                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        // String lectuerid=dataSnapshot.getKey();

                        // PDF s=new PDF(lectuer);
                        // mDatabase = FirebaseDatabase.getInstance().getReference("users").child(userID);
                        mDatabase.child("users").child(userID).child("booked").setValue(bookmarklist);

                        // mDatabase.child("booked").push().setValue(s);
                        Toast.makeText(context.getApplicationContext(), "You've booked in successfully", Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
}




            }
        });






        return convertView;
    }

}
