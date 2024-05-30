package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class locator {
    public locator(String element) {

        HashMap<String, String> el = new HashMap<>();
        String[] parts = element.split("=");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid locator format. Expected 'type=value'");
        }

        String locatorType = parts[0];
        String locatorValue = parts[1];

        WebElement foundElement;
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
    }
}
