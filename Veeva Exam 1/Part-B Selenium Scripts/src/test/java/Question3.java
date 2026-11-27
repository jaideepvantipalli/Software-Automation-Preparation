import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Question3 {
    public static void main(String[] args){
//        3. Multi-Window / Tab Handling
//        Problem Statement: Open a webpage containing a link that opens in a new browser tab. Click the link, switch to the newly opened tab, extract and print its page title, close the new tab, and switch control back to the parent window.
//        Evaluation Criteria:
//        Capturing parent window ID via getWindowHandle().
//        Iterating through getWindowHandles() to identify and switch to the child window.
//        Closing only the active tab (driver.close()) rather than the entire session (driver.quit()), followed by switching back to the parent handle.
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
        String parentWindow = driver.getWindowHandle();

        driver.findElement(By.xpath("//button[@id='newTabBtn']")).click();

        Set<String> windows = driver.getWindowHandles();
        System.out.println("Child Window titles : ");
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
            }
        }
        driver.close();

        driver.switchTo().window(parentWindow);

        System.out.println("Switching back to the parent window after closing the newly opened tab :"+driver.getTitle());


    }
}
