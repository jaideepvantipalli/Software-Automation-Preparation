import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Question1 {
    public static void main(String[] args) throws InterruptedException {
//        1.Dynamic Form Submission & Validation
//        Problem Statement: Write a Selenium script to navigate to a registration page, fill in text fields (Name, Email, Password), select a country from a standard <select> dropdown by visible text, check a "Terms & Conditions" checkbox only if it is not already selected, and click Submit.
//        Evaluation Criteria:
//        Proper use of Select class (selectByVisibleText()).
//        Conditional check using isSelected() before clicking the checkbox.
//        Verification of a success message after submission.
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("D:\\VEEVA\\Montly Test 1\\Part-B Selenium scripts\\src\\main\\resources\\Login.html");
        WebElement name = driver.findElement(By.id("name"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));

        WebElement country = driver.findElement(By.id("country"));
        WebElement checkbox = driver.findElement(By.id("terms"));
        WebElement submit = driver.findElement(By.id("submitBtn"));

        name.sendKeys("Jaideep Vantipalli");
        email.sendKeys("jaideep@gmail.com");
        password.sendKeys("11111111");

        Select countrySelect = new Select(country);
        countrySelect.selectByVisibleText("India");

        if(!checkbox.isSelected()){
            checkbox.click();
            System.out.println("Terms and conditions are accepted");
        }else System.out.println("Terms and conditions are not accepted");

        submit.click();

        Thread.sleep(2000);
        WebElement successMsg = driver.findElement(By.id("success-message"));

        if(successMsg.isDisplayed()){
            System.out.println("Retrieved Message : "+successMsg.getText());
        }else {
            System.out.println("Form Not submitted.");
        }

        Thread.sleep(2000);
        driver.quit();
    }

}
