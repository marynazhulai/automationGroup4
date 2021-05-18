package com.customertimes;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class AppTest 
{

    @Test
    public void shouldAnswerWithTrue()
    {
        System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)//Maven//Chromedriver for Maven/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        try {
            Thread.sleep(15_00);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.close();
    }
}
