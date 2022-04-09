package com.qa.base;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UIBasePage {
    private int timeOut = 10;
    private String parent;
    private static WebDriver driver;

    public void initWebDriver(){
        ChromeDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void quitWebDriver(){
        if(driver != null){
            driver.quit();
        }
    }

    public void openPage(String url){
        driver.get(url);
    }

    public void maximizeWindow(){
        driver.manage().window().maximize();
    }

    public UIBasePage getParentWindowHandle(){
        parent = driver.getWindowHandle();
        return this;
    }

    public UIBasePage switchToChildWindow(){
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        while(iterator.hasNext()) {
            String childWindow = iterator.next();
            if(!parent.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                System.out.println(driver.switchTo().window(childWindow).getTitle());
            }
        }
        return this;
    }

    public void switchToParentWindow(){
        driver.close();
        driver.switchTo().window(parent);
    }

    public void switchToIFrame(By element){
        driver.switchTo().frame(driver.findElement(element));
    }

    public String getAlertText(){
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    public void clickRadioButtonByIndex(By locator, int index){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            List<WebElement> radioButton = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            int size = radioButton.size();
            if(index <= size){
                radioButton.get(index).click();
            } else {
                System.out.println("Index out of bound");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void clickElement(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void typeText(By locator, String value){
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(value);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean selectFromSuggestions(By locator, int index){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            List<WebElement> listItems = element.findElements(By.tagName("li"));
            listItems.get(index).click();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void selectFromListByIndex(By locator, int index){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
            select.selectByIndex(index);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isVisible(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean isRadioButtonSelected(By locator, int index){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            List<WebElement> radioButton = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            int size = radioButton.size();
            if(index <= size){
                radioButton.get(index).isSelected();
                return true;
            } else {
                System.out.println("Index out of bound");
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getSelectedTextFromDropDown(By locator){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isCheckBoxSelected(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            return element.isSelected();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int getPriceSumInTable(By locator, int columnIndex){
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        List<WebElement> rows = element.findElements(By.tagName("tr"));
        for(int r=0; r < rows.size(); r++){
            List<WebElement> cells = rows.get(r).findElements(By.tagName("td"));
            for(int c=0; c < cells.size(); c++){
                if(c == columnIndex){
                    count += Integer.parseInt(cells.get(columnIndex).getText());
                }
            }
        }
        return count;
    }

    public void hoverToElement(By mainLocator, By suMenuLocator){
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement mainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mainLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainElement).perform();
        WebElement subElement = wait.until(ExpectedConditions.visibilityOfElementLocated(suMenuLocator));
        subElement.click();
    }

    public void waitSomeSeconds(int t){
        driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
    }
}
