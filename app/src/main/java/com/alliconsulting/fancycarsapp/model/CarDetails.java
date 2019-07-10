package com.alliconsulting.fancycarsapp.model;

import com.github.javafaker.Faker;

import java.util.Random;

public class CarDetails {
    String carName;
    String carMake;
    String carModel;
    String availability;

    static final String[] avails = {"In Dealership","Out of Stock", "Unavailable"};

    public CarDetails( Faker nameFaker ){

        carName = nameFaker.ancient().hero();
        carMake = nameFaker.company().name();
        carModel = nameFaker.funnyName().name();
        Random rand = new Random(System.currentTimeMillis());
        int randInt = rand.nextInt(3);
        availability = avails[randInt];
    }

    public String toString(){
        return carName + "\n" + carMake + "\n" + carModel + "\n" + availability;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


}
