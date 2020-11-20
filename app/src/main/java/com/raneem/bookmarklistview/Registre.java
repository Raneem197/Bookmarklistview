package com.raneem.bookmarklistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registre extends AppCompatActivity {


    EditText username1, email1, pass;
    TextView login_here;
    Button regester;
    private FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);


        username1 = (EditText) findViewById(R.id.editUsername);
        email1 = (EditText) findViewById(R.id.editEmail2);
        pass = (EditText) findViewById(R.id.editPassword);
        regester = (Button) findViewById(R.id.button);
        login_here = (TextView) findViewById(R.id.login_here);

        mFirebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


    }


    public void loginPage(View view){
        Intent log=new Intent(getApplicationContext(),Login.class);
        startActivity(log);

    }

    public void Register(View view){


        final String username=username1.getText().toString();
        final   String email_ll=email1.getText().toString();
        final   String password=pass.getText().toString();


        mFirebaseAuth.createUserWithEmailAndPassword(email_ll, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("my_stor", "createUserWithEmail:success");

                            String userID=task.getResult().getUser().getUid();
                            User userr=new User(username);


                            FirebaseUser user = mFirebaseAuth.getCurrentUser();


                            firebaseDatabase.getReference("users").child(userID).setValue(userr);


                            Intent ll =new Intent (getApplicationContext(),Login.class);
                            startActivity(ll);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("my_stor", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();


                        }

                    }
                });
    }
    }
