package com.customertimes.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class ValidateRandomValues {
   // @Test
    public void test(){
    //
    Random rand = new Random();
    int random = rand.nextInt(10);
    System.out.println("The result is " + random);
    Assert.assertTrue(random >= 0 && random <=10);

}




}
