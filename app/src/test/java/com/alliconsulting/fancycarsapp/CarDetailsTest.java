package com.alliconsulting.fancycarsapp;

import android.util.Log;

import com.alliconsulting.fancycarsapp.model.CarDetails;

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
    public void test_create_car_details(){
        CarDetails carDetails = new CarDetails();
        String carName = carDetails.getCarName();
        String carMake = carDetails.getCarMake();
        String carModel = carDetails.getCarMake();
        String availability = carDetails.getAvailability();

        System.out.println(carDetails.toString());

        assertTrue(carName!=null);
        assertTrue(carMake!=null);
        assertTrue(carModel!=null);
        assertTrue(availability!=null);
    }
}