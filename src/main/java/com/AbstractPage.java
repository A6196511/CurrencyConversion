package com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected void waitForElementIsEnabled(WebElement element) {
        new WebDriverWait(DriverProvider.webDriver(), Integer.parseInt(PropertyProvider.getProperty("COMMON_TIMEOUT"))).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        try {
            waitForElementIsEnabled(element);
            element.click();
        } catch (StaleElementReferenceException | ElementClickInterceptedException ex) {
            waitForElementIsEnabled(element);
            element.click();
        }
    }

    protected void waitForElementIsInvisible(By by) {
        new WebDriverWait(DriverProvider.webDriver(), Integer.parseInt(PropertyProvider.getProperty("COMMON_TIMEOUT"))).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitForElementIsVisible(WebElement element) {
        new WebDriverWait(DriverProvider.webDriver(), Integer.parseInt(PropertyProvider.getProperty("COMMON_TIMEOUT"))).until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementIsVisible(WebElement element, long timeOutInSeconds) {
        new WebDriverWait(DriverProvider.webDriver(), timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
    }


    protected void selectByVisibleText(WebElement element, String value) {
        try {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(value);
        } catch (StaleElementReferenceException ex) {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(value);
        }
    }

    public void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) DriverProvider.webDriver()).executeScript(scrollElementIntoMiddle, element);
    }

    protected String getText(WebElement element) {
        try {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            return element.getText();
        } catch (StaleElementReferenceException ex) {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            return element.getText();
        }
    }

    protected String getValue(WebElement element) {
        waitForElementIsEnabled(element);
        scrollToElement(element);
        return element.getAttribute("value");
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            element.sendKeys(text);
        } catch (StaleElementReferenceException ex) {
            waitForElementIsEnabled(element);
            scrollToElement(element);
            element.sendKeys(text);
        }
    }

    protected WebElement findElementByTillNotNull(By by, long timeOutInSeconds) throws InterruptedException {
        if (timeOutInSeconds == 0)
            return null;
        try {
            WebElement element = DriverProvider.webDriver().findElement(by);
            if (element != null)
                return element;
        } catch (NoSuchElementException | NullPointerException e) {
            Thread.sleep(1000);
            return findElementByTillNotNull(by, --timeOutInSeconds);
        }
        return null;
    }

    protected WebElement findElementByTillNotNull(By by) throws InterruptedException {
        return findElementByTillNotNull(by, Integer.parseInt(PropertyProvider.getProperty("COMMON_TIMEOUT")));
    }

    protected WebElement findElementBy(By by) {
        WebElement element;
        try {
            element = DriverProvider.webDriver().findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
        return element;
    }

    protected boolean isElementNotPresent(WebElement element) {
        return !(element != null && element.isDisplayed());
    }
}
