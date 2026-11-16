package com.selenium.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        //to
        driver.navigate().to("https://www.youtube.com");
        //back
        driver.navigate().back();
        //forward
        driver.navigate().forward();
        //refresh
        driver.navigate().refresh();
        System.out.println("refreshed");

        Thread.sleep(5000);
        driver.quit();

    }
}
