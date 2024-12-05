package test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenURL {
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "C:/Users/MayuriKamtikar/Desktop/Tools/Chrome_Driver/chromedriver-win32/chromedriver.exe");

   
        WebDriver driver = new ChromeDriver();

        try {
            
            String url = "https://d3pv22lioo8876.cloudfront.net/tiptop/";
            driver.get(url);
            driver.manage().window().maximize();

        
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 

        
            WebElement disabledInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@name='my-disabled']")));
            System.out.println("1) Is  xpath .//input[@name='my-disabled'] input disabled? " + !disabledInput.isEnabled());

            
            
           //-----------------------------------------------------------// 
            
          
            WebElement readonlyInput1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//input[@value='Readonly input']")));
            WebElement readonlyInput2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@readonly]")));
            System.out.println("2) Read only is disabled  using 1st xpath " + (readonlyInput1.getAttribute("readonly") != null));
            System.out.println("2) Read only is disabled  using 2nd xpath " + (readonlyInput2.getAttribute("readonly") != null));
            //-----------------------------------------------------------// 
         
            WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@name='my-select']"))); 
            wait.until(ExpectedConditions.visibilityOf(dropdown)); // Ensure visibility

            int dropdownSize1 = dropdown.findElements(By.tagName("option")).size();
            int dropdownSize2 = driver.findElements(By.xpath("//select[@name='my-select']/option")).size(); 
            System.out.println("3) Dropdown size using 1st xpath :  " + dropdownSize1);
            System.out.println("3) Dropdown size using 2nd xpath :  " + dropdownSize2);

            //-----------------------------------------------------------// 
            
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit-form")));
            System.out.println("4) submit button disabled when there is no input? " + !submitButton.isEnabled());

            //-----------------------------------------------------------// 
            
            
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-name")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("my-password")));
            nameField.sendKeys("TestName");
            passwordField.sendKeys("TestPassword");
            
            //-----------------------------------------------------------// 
            
        
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)); 
            System.out.println("5)Submit button enabled after entering Name and Password? " + submitButton.isEnabled());

        
            submitButton.click();

            //-----------------------------------------------------------// 
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            System.out.println("6) 'Received!' message displayed? " + messageElement.isDisplayed());

            //-----------------------------------------------------------// 
            String currentURL = driver.getCurrentUrl();
            System.out.println("URL after form submission: " + currentURL);
           

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
