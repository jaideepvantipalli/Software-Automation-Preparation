package com.selenium.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class BrowserInformationDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        //driver.navigate().to("https://www.youtube.com");
        System.out.println("Title : "+driver.getTitle());
        System.out.println("Current Url : "+driver.getCurrentUrl());
        System.out.println("Page Source : "+driver.getPageSource());
        System.out.println("Page windown handle: "+driver.getWindowHandle());
        Set<String> allWindowHandles = driver.getWindowHandles();
        System.out.println("Current Window Handle : "+allWindowHandles);
        Thread.sleep(5000);
        driver.quit();
    }
}
