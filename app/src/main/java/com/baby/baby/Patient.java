package com.baby.baby;

import java.util.List;

public class Patient {
    String name;
    List<String> caretakers;


    public Patient(String name, List<String> caretakers) {
        this.name = name;
        this.caretakers = caretakers;
    }
}
