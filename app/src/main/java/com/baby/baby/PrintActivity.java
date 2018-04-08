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

import java.util.ArrayList;
import java.util.List;

public class PrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
        final TextView textView = (TextView) findViewById(R.id.textView);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.child("0").child("notes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> notes = dataSnapshot.getChildren();
                final List<Note> noteObjects = new ArrayList<Note>();
                for (DataSnapshot note : notes) {
                    String date = (String) note.child("date").getValue();
                    String time = (String) note.child("time").getValue();
                    String text = (String) note.child("text").getValue();
                    String patient = (String) note.child("patient").getValue();
                    String caretaker = (String) note.child("caretaker").getValue();
                    Note noteObj = new Note(date, time, text, patient, caretaker);
                    noteObjects.add(noteObj);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
