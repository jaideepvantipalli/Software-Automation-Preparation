package com.selenium.basics;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class ImplicitWaitDemo {

    public static void main(String[] args) {

        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://youtube.com");
        WebElement heading = driver.findElement(By.tagName("h1"));
        System.out.println(heading.getText());
        driver.quit();
    }
}
