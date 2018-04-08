package com.baby.baby;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class AddNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void writeNewNote(String text, String patient, String caretaker, DatabaseReference ref) {
        String newID  = Integer.toString(ThreadLocalRandom.current().nextInt(0, 100000));
        LocalDateTime datetime = LocalDateTime.now();
        int date = datetime.getYear() * 100 + datetime.getMonthValue() * 100 + datetime.getDayOfMonth();
        int time = datetime.getHour() * 100 + datetime.getMinute();
        Note newNote = new Note(Integer.toString(date), Integer.toString(time),
                                text, patient, caretaker);
        ref.child("0").child("notes").setValue(newID);
        ref.child("0").child("notes").child(newID).setValue(newNote);

    }
}
