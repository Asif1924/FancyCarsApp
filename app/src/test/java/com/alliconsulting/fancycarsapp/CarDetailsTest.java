package com.alliconsulting.fancycarsapp;

import android.util.Log;

import com.alliconsulting.fancycarsapp.model.CarDetails;
import com.github.javafaker.Faker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CarDetailsTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test_can_create_car_details(){
        System.out.println("=============test_can_create_car_details");

        Faker carFaker = new Faker();
        CarDetails carDetails = new CarDetails(carFaker);
        String carName = carDetails.getCarName();
        String carMake = carDetails.getCarMake();
        String carModel = carDetails.getCarMake();
        String availability = carDetails.getAvailability();

        System.out.println("Car details:\n" + carDetails.toString());

        assertTrue(carName!=null);
        assertTrue(carMake!=null);
        assertTrue(carModel!=null);
        assertTrue(availability!=null);
    }

    @Test
    public void test_can_create_2_consecutive_car_details_which_are_different(){
        System.out.println("=============test_can_create_2_consecutive_car_details_which_are_different");
        Faker carFaker = new Faker();

        CarDetails carDetails = new CarDetails(carFaker);

        String carName1 = carDetails.getCarName();
        String carMake1 = carDetails.getCarMake();
        String carModel1 = carDetails.getCarMake();
        String availability1 = carDetails.getAvailability();

        System.out.println("Car details 1:\n" + carDetails.toString());

        assertTrue(carName1!=null);
        assertTrue(carMake1!=null);
        assertTrue(carModel1!=null);
        assertTrue(availability1!=null);

        CarDetails carDetails2 = new CarDetails(carFaker);

        String carName2 = carDetails2.getCarName();
        String carMake2 = carDetails2.getCarMake();
        String carModel2 = carDetails2.getCarMake();
        String availability2 = carDetails2.getAvailability();

        System.out.println("Car details 2:\n" + carDetails2.toString());

        assertTrue(carName2!=null);
        assertTrue(carMake2!=null);
        assertTrue(carModel2!=null);
        assertTrue(availability2!=null);

        assertTrue(!carName1.equals(carName2));
        assertTrue(!carMake1.equals(carMake2));
        assertTrue(!carModel1.equals(carModel2));
        //assertTrue(!availability1.equals(availability2));

    }
}