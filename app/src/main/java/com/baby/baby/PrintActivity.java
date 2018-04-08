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
        String s = "hi";
        textView.setText(s);

        final FirebaseDatabase database = FirebaseDatabase.getInstance(); // getInstance("https://chrmid-52856.firebaseio.c");
        DatabaseReference ref = database.getReference(); // no args means reference to top level
        //Quequery = ref.endAt("Adam"); // bad?
        //query.addListenerForSingleValueEvent(new ValueEventListener() {

        ref.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Iterable<DataSnapshot> notes = dataSnapshot.child("notes").getChildren();
                final List<Note> noteObjects = new ArrayList<Note>();
                for (DataSnapshot note : notes) {
                    //Note noteObj = note.getValue(Note.class);
                    String date    = (String) note.child("date").getValue();
                    String time = (String) note.child("time").getValue();
                    String text = (String) note.child("text").getValue();
                    String patient = (String) note.child("patient").getValue();
                    String caretaker = (String) note.child("caretaker").getValue();

                    //boolean ex = note.child(note.getKey()).exists();
                    //boolean ex = note.child("caretaker").exists();
                    //textView.setText("current key: " + note.getKey() + " note exists: " + ex + " note contents: " + text);
                    Note noteObj = new Note(date, time, text, patient, caretaker);
                    noteObjects.add(noteObj);
                    //textView.setText("note contents: " + noteObj.getCaretaker());
                }
                /*
                Iterable<DataSnapshot> patients = dataSnapshot.child("patients").getChildren();
                List<Patient> patientObjects = new ArrayList<Patient>();
                for (DataSnapshot patient : patients) {
                    String patientName = patient.getKey();

                    patientObjects.add(new Patient(patientName, ));
                }
                */
                int i = 0;
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot singleSnapshot : children) {
                    i += 1;
                    String name = (String) singleSnapshot.getKey();
                    System.out.println(name);
                    //textView.setText("name is " + name + ", ok? Iteration " + i);
                    //String message = (String) singleSnapshot.child("patients").getValue();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        // textView.setText(s);
    }
}
