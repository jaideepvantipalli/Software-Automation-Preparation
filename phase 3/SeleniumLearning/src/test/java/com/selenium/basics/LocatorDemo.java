package com.selenium.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class LocatorDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            driver.get("file:///C:/Selenium/login.html");

            //1.Id Locator
            WebElement username = driver.findElement(By.id("username"));

            username.sendKeys("Jaideep");

            // 2. Name locator
            WebElement password = driver.findElement(By.name("pass"));

            password.sendKeys("Test123");

            // 3. ClassName locator
            WebElement login =driver.findElement(By.className("login"));
            login.click();

            // 4. TagName locator
            List<WebElement> inputs = driver.findElements(By.tagName("input"));

            System.out.println("Total inputs: " + inputs.size());

            // 5. LinkText locator
            WebElement link = driver.findElement(By.linkText("Forgot Password?"));

            System.out.println("Link: " + link.getText());

            // 6. PartialLinkText locator
            WebElement partialLink = driver.findElement(By.partialLinkText("Forgot"));

            System.out.println("Partial link: " + partialLink.getText());

            // 7. CSS Selector
            WebElement cssElement = driver.findElement(By.cssSelector("#username"));

            System.out.println("CSS element found: " + cssElement.isDisplayed());

            // 8. XPath
            WebElement loginButton = driver.findElement(By.xpath("//button[@id='loginBtn']"));

            //By.xpath("//tagname[@attribute = 'value']);
            loginButton.click();

        } finally {
            driver.quit();
        }
    }
}
