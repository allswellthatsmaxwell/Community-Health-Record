package com.baby.baby;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

        ref.child("0").child("caretakers").addValueEventListener(new ValueEventListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> caretakers = dataSnapshot.getChildren();
                final List<Caretaker> caretakerObjects = new ArrayList<Caretaker>();
                for (DataSnapshot caretaker : caretakers) {
                    String name = (String) caretaker.child("name").getValue();
                    String role = (String) caretaker.child("role").getValue();
                    String accessLevel = (String) caretaker.child("access_level").getValue();
                    HashMap<String, String> patientsMap = (HashMap) caretaker.child("patients").getValue();
                    List<String> patients = new ArrayList<String>(patientsMap.keySet());
                    Caretaker caretakerObj = new Caretaker(name, role, patients, accessLevel);
                    caretakerObjects.add(caretakerObj);
                }
                for (Caretaker caretakerObj : caretakerObjects) {
                    Log.d("READ_SUCCESS", caretakerObj.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }
}
