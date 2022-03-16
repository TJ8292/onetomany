package com.kuiteul.dbeaver.domain;

import java.util.Objects;

public class Pet {
    private String commonName;
    private String color;
    private int person_Id;

    public Pet() {
    }



    public Pet(String commonName, String color, int person_Id) {
        this.commonName = commonName;
        this.color = color;
        this.person_Id = person_Id;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPerson_Id(int person_Id) {
        this.person_Id = person_Id;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getColor() {
        return color;
    }

    public int getPerson_Id() {
        return person_Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return person_Id == pet.person_Id && Objects.equals(commonName, pet.commonName) && Objects.equals(color, pet.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commonName, color, person_Id);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "commonName='" + commonName + '\'' +
                ", color='" + color + '\'' +
                ", person_Id=" + person_Id +
                '}';
    }
}
