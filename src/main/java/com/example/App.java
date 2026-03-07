package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class App {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // open website
        driver.get("https://automationexercise.com/");

        Thread.sleep(3000);

        // try closing popup ad if present
        try {
            driver.findElement(By.xpath("//svg")).click();
            System.out.println("Ad popup closed");
        } catch (Exception e) {
            System.out.println("No popup ad");
        }

        // locate first product add-to-cart button
        WebElement product =
                driver.findElement(By.cssSelector("a[data-product-id='1']"));

        // scroll to product so ad/banner doesn't block it
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", product);

        Thread.sleep(1000);

        // click add to cart
        product.click();

        System.out.println("Product added to cart");

        Thread.sleep(4000);

        driver.quit();
    }
}
