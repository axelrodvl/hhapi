package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Tools {
    public static WebElement getLoadedElement(WebDriver driver, int timeoutInMs, By by) throws Exception {
        final int onceSleepTimeInMs = 50;
        int currentSleepTimeInMs = 0;

        while((driver.findElements(by).size() == 0) && (currentSleepTimeInMs < timeoutInMs)) {
            TimeUnit.MILLISECONDS.sleep(onceSleepTimeInMs);
            currentSleepTimeInMs += onceSleepTimeInMs;
        }

        if(currentSleepTimeInMs >= timeoutInMs)
            throw new Exception("Element " + by.toString() + " not found after timeout");
        else
            return driver.findElement(by);
    }
}
