package com.kuiteul.dbeaver.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    private int id;
    private String first_name;
    private String last_name;
    private Gender gender;

    private  List<Pet> pets= new ArrayList<>();
    private  List<Car> cars= new ArrayList<>();

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Person() {
    }



    public Person(int id, String first_name, String last_name, Gender gender) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Pet> getPets() {
        return pets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(first_name, person.first_name) && Objects.equals(last_name, person.last_name) && gender == person.gender && Objects.equals(pets, person.pets) && Objects.equals(cars, person.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, gender, pets, cars);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender=" + gender +
                ", pets=" + pets +
                ", cars=" + cars +
                '}';
    }
}
