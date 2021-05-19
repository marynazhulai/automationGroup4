package com.customertimes.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider {
    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    }

    @Test(dataProvider = "data-provider")
    public void testMethod(int a, int b, int c) {
        int sum = a+b;
        System.out.println(sum + " and " + c);
    }
}
