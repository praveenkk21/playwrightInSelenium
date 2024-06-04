package page;

import com.google.common.collect.BoundType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class page implements WebElement {
    private WebDriver driver;
    private WebElement foundElement;
    private List<WebElement> foundElements;
    public page(WebDriver driver){
        this.driver=driver;
        System.out.println("# Playwright-Selenium Bridge : Leverage Playwright Features in Selenium Tests #");
        System.out.println("************** *************** **************** ***************** *************");
        System.out.println("************** ************ Coded by Praveen Kumar B ************ *************");
        System.out.println("************** *************** **************** ***************** *************");
        System.out.println();
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

    @Override
    public void submit() {

    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getTagName() {
        return "";
    }

    @Override
    public String getAttribute(String name) {
        return "";
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return "";
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of();
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return "";
    }

    //Custom functions coded by <Praveen Kumar B>
    public void selectDropdown(String text) {
        //WebElement dropdownElement = locator(element);
        Select dropdown = new Select(foundElement);
        dropdown.selectByVisibleText(text); // Or use selectByIndex(int index)
    }

    public page locator(String element) {
        HashMap<String, String> el = new HashMap<>();
        String[] parts = element.split("=");

        String locatorType = parts[0];
        String locatorValue ;
        if(parts[0].equals("xpath") && parts.length>2){
             locatorValue=parts[1]+"="+parts[2];
        }
        else locatorValue = parts[1];

        if (parts.length != 2 && !(parts[0].equals("xpath"))) {
            throw new IllegalArgumentException("Invalid locator format. Expected 'type=value'");
        }

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
            case "text":
                foundElement = driver.findElement(By.xpath("//*[text()='"+locatorValue+"']"));
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

    public WebElement getByLabel(String label){
        foundElement = driver.findElement(By.xpath("//label[text()='"+label+"']"));
        return this.foundElement;
    }

    public WebElement getByText(String text){
      foundElement = driver.findElement(By.xpath("//*[text()='"+text+"']"));
        return this.foundElement;
    }

    public WebElement getByPlaceholder(String placeholderText){
        foundElement = driver.findElement(By.xpath("//input[@placeholder='" + placeholderText + "']"));
        return this.foundElement;
    }

    public boolean toBeVisible(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000)); // Change timeout value if needed
        wait.until(ExpectedConditions.visibilityOf(foundElement));
        // Verify element visibility (optional)
        return foundElement.isDisplayed();
    }

    public void check(){
        if (!foundElement.isSelected()) {
            foundElement.click();
        }
    }

    public void uncheck(){
        if (foundElement.isSelected()) {
            foundElement.click();
        }
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

    public void goBack() {
        driver.navigate().back();
    }

    public boolean toHaveTitle(String title) {
        return driver.getTitle().equals(title);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
