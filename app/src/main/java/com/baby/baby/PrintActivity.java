package com.baby.baby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        final TextView textView = (TextView) findViewById(R.id.textView);
        String s = "hi";
        textView.setText(s);

        final FirebaseDatabase database = FirebaseDatabase.getInstance(); // getInstance("https://chrmid-52856.firebaseio.c");
        DatabaseReference ref = database.getReference(); // no args means reference to top level
        //Quequery = ref.endAt("Adam"); // bad?
        //query.addListenerForSingleValueEvent(new ValueEventListener() {

        ref.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                int i = 0;
                for(DataSnapshot singleSnapshot : children) {
                    i += 1;
                    String name = (String) singleSnapshot.getKey();
                    System.out.println(name);
                    textView.setText("name is " + name + ", ok? Iteration " + i);
                    //String message = (String) singleSnapshot.child("patients").getValue();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        // textView.setText(s);
    }
}
