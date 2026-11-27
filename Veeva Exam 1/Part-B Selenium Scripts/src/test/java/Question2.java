import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Question2 {
    public static void main(String[] args){
//        2.	Handling JavaScript Alerts & Pop-ups
//        Problem Statement: Automate a scenario where clicking a button triggers a JavaScript Confirmation Alert. Print the alert text to the console, accept the alert, and then trigger a Prompt Alert, enter "Automation Test" into it, and dismiss it.
//        Evaluation Criteria:
//        Switching context using driver.switchTo().alert().
//          Correct usage of getText(), accept(), sendKeys(), and dismiss().
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert2 = driver.switchTo().alert();
        System.out.println(alert2.getText());
        alert2.accept();

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert promptAlert = driver.switchTo().alert();
        System.out.println(promptAlert.getText());
        promptAlert.sendKeys("Automation Script");
        promptAlert.dismiss();

        driver.quit();
    }
}
