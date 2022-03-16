package com.kuiteul.dbeaver.domain;

import java.util.Objects;

public class Car {
    private int carId;
    private String make;
    private String model;
    private String color;
    private String modelYear;
    private int personId;

    public Car() {
    }

    public Car(int carId, String make, String model, String color, String modelYear, int personId) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.color = color;
        this.modelYear = modelYear;
        this.personId = personId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return personId == car.personId && Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(modelYear, car.modelYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, color, modelYear, personId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", modelYear='" + modelYear + '\'' +
                ", personId=" + personId +
                '}';
    }
}
