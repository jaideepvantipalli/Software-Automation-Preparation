package com.selenium.basics;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExplicitWaitDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
            // Wait until Start button is clickable
            WebElement startButton = wait.until(
                                ExpectedConditions.elementToBeClickable(
                                        By.cssSelector("#start button")
                                )
                            );

            startButton.click();

            // Wait until result becomes visible
            WebElement result = wait.until(
                                    ExpectedConditions.visibilityOfElementLocated(
                                            By.id("finish")
                                    )
                            );
            System.out.println(result.getText());

        } finally {
            driver.quit();
        }
    }
}
