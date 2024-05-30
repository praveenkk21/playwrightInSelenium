package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import page.*;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;


@Test
public class AppTest
{
    WebDriver driver;
    //System.out.println(org.openqa.selenium.version.Version.getVersion());

    @Test(priority=0) //,groups = {"dont run"}
    public static WebDriver Chromedriver(String url)
    {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        page page=new page(driver);
        page.goTo(url);
        page.locator("id=test");
        page.locator("id=test").click();
        page.locator("id=test").selectDropdown("test");
        page.locator("id=test").fill("test");
        page.getByRole("button", "{ name: 'Sign in' }");
        page.getByLabel("text").click();
        WebElement n= (WebElement) page.getByText("Welcome, John");
        page.expect(n).toBeVisible();
        List<WebElement> n1= page.locators("id=test");
        page.expect(n1).toHaveCount(1);


        driver.get(url);
        return driver;
    }
    @AfterTest
    public void browserClose() throws SQLException {
        driver.close();
        driver.quit();
    }
}


