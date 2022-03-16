package com.kuiteul.dbeaver.domain;

public enum Gender {
    MALE("male"),
    FEMALE("female");


    private final String gender;
    public String getGender() {
        return gender;
    }

    Gender (String gender) {
        this.gender = gender;
    }
}
