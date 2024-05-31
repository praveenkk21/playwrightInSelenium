package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import page.*;
import java.time.Duration;
import java.util.List;


@Test
public class AppTest
{
    public static WebDriver driver;

    @BeforeTest
    static void chromeDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    static void testMethod()
    {
        page page=new page(driver);
        page.goTo("https://the-internet.herokuapp.com/");
        WebElement element= page.locator("xpath=//*[text()='Dropdown']");
        page.toHaveTitle("The Internet");
        page.locator("xpath=//*[text()='Dropdown']").click();
        page.locator("id=dropdown").selectDropdown("Option 1");
        page.goBack();
        page.getByText("Inputs").click();
        page.locator("xpath=//*[@type='number']").fill("test");
        page.goBack();
        //page.getByLabel("text").click();
        page.expect(page.getByText("Checkboxes")).toBeVisible();
        page.getByText("Checkboxes").click();
        page.locator("text= checkbox 1").check();
        page.locator("text= checkbox 1").uncheck();
        page.goBack();
        List<WebElement> n1= page.locators("xpath=//li");
        page.expect(n1).toHaveCount(43);
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}


