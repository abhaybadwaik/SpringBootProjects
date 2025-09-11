package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginEidiko {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("http://10.0.0.12:8080/eidiko_workorder/#/eidiko_workorder/");
        driver.manage().window().maximize();

        Thread.sleep(3000);

        // Use more stable selectors: input type
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345");

        Thread.sleep(1000);

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        System.out.println("✅ Login submitted.");

        Thread.sleep(3000);
        driver.quit();
    }
}