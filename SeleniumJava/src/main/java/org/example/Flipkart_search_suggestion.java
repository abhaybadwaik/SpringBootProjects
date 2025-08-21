package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Flipkart_search_suggestion {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.flipkart.com");


        // Search input
        WebElement searchBar = driver.findElement(By.className("Pke_EE"));
        searchBar.sendKeys("shoes");

        Thread.sleep(3000);

        // Suggestions list
        List<WebElement> searchSuggestions = driver.findElements(By.xpath("//li[@class='_3D0G9a']"));

        for (WebElement suggestion : searchSuggestions) {
            String text = suggestion.getText();
            System.out.println(text);
            if (text.equalsIgnoreCase("shoes rack with door")) {
                suggestion.click();
                break;
            }
        }

    }
}