package com.selenium.basics;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;

public class FluentWaitDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://example.com");
            Wait<WebDriver> wait = new FluentWait<>(driver)
                                    .withTimeout(Duration.ofSeconds(20))
                                    .pollingEvery(Duration.ofSeconds(2))
                                    .ignoring(NoSuchElementException.class);

            WebElement heading = wait.until(d -> d.findElement(By.tagName("h1")));
            System.out.println(heading.getText());
        } finally {
            driver.quit();
        }
    }
}