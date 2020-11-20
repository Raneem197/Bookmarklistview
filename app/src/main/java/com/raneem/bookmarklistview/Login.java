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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Login extends AppCompatActivity {


    TextView notuser;
    EditText email, password;
    ;
    Button login;
    TextView forgotPassword_textButton;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mDatabase;
    StorageReference storageRef;
    FirebaseDatabase firebaseDatabase;
    String sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mFirebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button3);
        forgotPassword_textButton = (TextView) findViewById(R.id.texetButton);
        notuser = (TextView) findViewById(R.id.textView6);
        storageRef = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();


    }


    public void RegisterPage(View view) {


        Intent register = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(register);
    }

    public void Login(View view) {
        String uEmail = email.getText().toString();
        String uPassword = password.getText().toString();
        mFirebaseAuth.signInWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);


                            // Sign in success, update UI with the signed-in user's information
                            Log.d("my_stor", "createUserWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();

                            Toast.makeText(getApplicationContext(), "You've logged in successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("my_stor", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "login failed.\nMake sure you entered valid email and password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });






    }
}