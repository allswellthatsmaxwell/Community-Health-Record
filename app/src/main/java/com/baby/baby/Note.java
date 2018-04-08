package com.baby.baby;

import java.time.Instant;

public class Note {
    String date;
    String time;
    String text;
    String patient;
    String caretaker;

    public Note(String date, String time, String text, String patient, String caretaker) {
        this.date = date;
        this.time = time;
        this.text = text;
        this.patient = patient;
        this.caretaker = caretaker;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public String getCaretaker() {
        return caretaker;
    }

    public String getPatient() {
        return patient;
    }
}