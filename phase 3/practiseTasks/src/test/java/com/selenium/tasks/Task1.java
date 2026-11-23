package com.selenium.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task1 {
    public static void main(String args[]){
//        Write locators for these requirements:
//        1	Identify student name using ID
//        2	Identify email using name
//        3	Identify password using CSS selector
//        4	Identify Register button using XPath
//        5	Identify Login link using linkText
//        6	Identify all input elements using tagName
//        7	Identify student name using XPath
//        8	Identify Register button using CSS selector
//        9	Identify all elements with class form-control
//        10 Identify the heading using XPath text
        WebDriver driver = new ChromeDriver();
        driver.get("D:\\VEEVA\\phase 3\\practiseTasks\\src\\main\\resources\\registration.html");

        WebElement name=driver.findElement(By.id("studentName"));
        name.sendKeys("jaideep");
        WebElement email=driver.findElement(By.name("email"));

        WebElement password=driver.findElement(By.cssSelector("input#studentPassword"));

        WebElement registerbtn=driver.findElement(By.xpath("//button[@id='registerBtn']"));

        WebElement loginlink=driver.findElement(By.linkText("Already registered? Login"));
        System.out.println("Link: " + loginlink.getText());

        List<WebElement> allinputs = driver.findElements(By.tagName("input"));

        WebElement studentname2 = driver.findElement(By.xpath("//*[@id='studentName']"));

        WebElement studentemail2 = driver.findElement(By.cssSelector(".btn"));

        List<WebElement> allElementsWithFormcontrol = driver.findElements(By.className(".form-control"));

        WebElement heading = driver.findElement(By.xpath("//h2[text()='Student Registration']"));
        driver.quit();
    }
}
