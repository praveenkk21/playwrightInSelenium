package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class page {
    private WebDriver driver;
    private WebElement foundElement;
    private List<WebElement> foundElements;
    public page(WebDriver driver){
        this.driver=driver;
    }

    public void goTo(String url){
        driver.get(url);
    }

    public void fill(String value) {
        foundElement.clear();
        foundElement.sendKeys(value);
    }

    public void click() {
        foundElement. click();
    }

    public void selectDropdown(String value) {
        //WebElement dropdownElement = locator(element);
        Select dropdown = new Select(foundElement);
        dropdown.selectByVisibleText(value); // Or use selectByIndex(int index)
    }

    public page locator(@org.jetbrains.annotations.NotNull String element) {
        HashMap<String, String> el = new HashMap<>();
        String[] parts = element.split("=");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid locator format. Expected 'type=value'");
        }

        String locatorType = parts[0];
        String locatorValue = parts[1];

        switch (locatorType.toLowerCase()) {
            case "id":
                foundElement = driver.findElement(By.id(locatorValue));
                break;
            case "name":
                foundElement = driver.findElement(By.name(locatorValue));
                break;
            case "xpath":
                foundElement = driver.findElement(By.xpath(locatorValue));
                break;
            case "cssselector":
            case "css": // Handle both "cssselector" and "css" variations
                foundElement = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "linktext":
                foundElement = driver.findElement(By.linkText(locatorValue));
                break;
            case "partiallinktext":
                foundElement = driver.findElement(By.partialLinkText(locatorValue));
                break;
            case "tagname":
                foundElement = driver.findElement(By.tagName(locatorValue));
                break;
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
        return this;
    }

    public page getByRole(String role, String name){
        foundElement = driver.findElement(By.xpath("//button[text()='Sign in']"));
        return this;
    }

    public page getByLabel(String label){
        foundElement = driver.findElement(By.xpath("//label[text()='"+label+"'])"));
        return this;
    }

    public page getByText(String text){
        foundElement = driver.findElement(By.xpath("//*[text()='"+text+"'])"));
        return this;
    }

    public boolean toBeVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000)); // Change timeout value if needed
        wait.until(ExpectedConditions.visibilityOf(foundElement));

            // Verify element visibility (optional)
        return foundElement.isDisplayed();
    }

    public page expect(WebElement foundElement){
        if (foundElement != null) {
            System.out.println("Element is found : "+ foundElement);
        } else {
            System.out.println("Element not found!");
        }
        return this;
    }

    public page expect(List<WebElement> foundElements){
        if (foundElements != null) {
            System.out.println("Element is found : "+ foundElements);
        } else {
            System.out.println("Element not found!");
        }
        return this;
    }
    public boolean toHaveCount(int count){
        // Find all matching elements
        int productCount = foundElements.size();
        // Verify there's only i no 0f element
        return productCount == count;
    }

    public List<WebElement> locators(String element) {
        HashMap<String, String> el = new HashMap<>();
        String[] parts = element.split("=");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid locator format. Expected 'type=value'");
        }

        String locatorType = parts[0];
        String locatorValue = parts[1];

        switch (locatorType.toLowerCase()) {
            case "id":
                foundElements = driver.findElements(By.id(locatorValue));
                break;
            case "name":
                foundElements = driver.findElements(By.name(locatorValue));
                break;
            case "xpath":
                foundElements = driver.findElements(By.xpath(locatorValue));
                break;
            case "cssselector":
            case "css": // Handle both "cssselector" and "css" variations
                foundElements = driver.findElements(By.cssSelector(locatorValue));
                break;
            case "linktext":
                foundElements = driver.findElements(By.linkText(locatorValue));
                break;
            case "partiallinktext":
                foundElements = driver.findElements(By.partialLinkText(locatorValue));
                break;
            case "tagname":
                foundElements = driver.findElements(By.tagName(locatorValue));
                break;
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
        return foundElements;
    }
}
