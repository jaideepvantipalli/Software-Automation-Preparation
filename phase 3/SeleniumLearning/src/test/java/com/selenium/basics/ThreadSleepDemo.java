package com.selenium.basics;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThreadSleepDemo {

    public static void main(String[] args)
            throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://example.com");

        Thread.sleep(5000);

        System.out.println(driver.getTitle());

        driver.quit();
    }
}