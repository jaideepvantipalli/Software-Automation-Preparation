package com.selenium.tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Task2 {
    public static void main(String[] args){

//        Use the registration.html file from this lesson.
//        1. Enter Jaideep into the username field.
//        2. Clear the username and enter Vantipalli Jaideep.
//        3. Enter an email address and retrieve its value using getAttribute().
//        4. Retrieve the registration heading using getText().
//        5. Select the Female radio button.
//        6. Select the Terms checkbox only if it is not already selected.
//        7. Verify whether the Register button is enabled.
//        8. Print whether the password field is displayed.
//        9. Click Register.
//        10. Retrieve and print the success message.
        WebDriver driver = new EdgeDriver();
        driver.get("D:\VEEVA\phase 3\practiseTasks\src\main\resources\studentRegistration.html");

        // 1. Enter Jaideep into the username field.
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("Jaideep");

        //        2. Clear the username and enter Vantipalli Jaideep.
        userName.clear();
        userName.sendKeys("Vantipalli Jaideep");

        //        3. Enter an email address and retrieve its value using getAttribute().
        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("jaideepvantipalli@gmail.com");
        String emailValue = email.getAttribute("value");
        System.out.println("Email: " + emailValue);

        //        4. Retrieve the registration heading using getText().
        String headingText = driver.findElement(By.xpath("//h2[@id='heading']")).getText();
        System.out.println("Heading: " + headingText);

        //        5. Select the Female radio button.

        WebElement female = driver.findElement(By.id("female"));

        if(!female.isSelected()){
            female.click();
        }

        //        6. Select the Terms checkbox only if it is not already selected.
        WebElement terms = driver.findElement(By.id("terms"));
        if(!terms.isSelected()){
            terms.click();
        }

        //        7. Verify whether the Register button is enabled.
        WebElement btn = driver.findElement(By.id("registerBtn"));
        if(btn.isEnabled()){
            System.out.println("Button is Enabled to Click");
        }else{
            System.out.println("Button is Disabled to Click");
        }

        //        8. Print whether the password field is displayed.

        WebElement passwordField = driver.findElement(By.id("password"));
        if(passwordField.isDisplayed()){
            System.out.println("Password Field is Displayed");
        }else System.out.println("Password Field is not Displayed");

        //        9. Click Register.
        WebElement registerBtn = driver.findElement(By.id("registerBtn"));
        registerBtn.click();


        //        10. Retrieve and print the success message.
        WebElement registrationMsg =driver.findElement(By.id("message"));
        if(!registrationMsg.getText().isEmpty()){
            System.out.println(registrationMsg.getText());
        }else{
            System.out.println("Registration Msg is Empty");
        }

        driver.quit();
    }
}
