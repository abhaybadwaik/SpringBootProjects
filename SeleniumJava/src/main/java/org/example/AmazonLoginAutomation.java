package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonLoginAutomation {
    public static void main(String[] args) throws InterruptedException {
        // Set ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        // Open Amazon login page
        driver.get("https://www.amazon.com/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26useRedirectOnSuccess%3D1%26signIn%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=usflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
        driver.manage().window().maximize();
        Thread.sleep(5000);

        // Enter email
        WebElement email = driver.findElement(By.cssSelector("input#ap_email"));
        email.sendKeys("badwaikabhay@gmail.com");
        Thread.sleep(3000);

        // Click continue
        WebElement continu = driver.findElement(By.cssSelector("input#continue"));
        continu.click();
        Thread.sleep(3000);

        // Enter password
        WebElement password = driver.findElement(By.cssSelector("input#ap_password"));
        password.sendKeys("Abhay@123");
        Thread.sleep(3000);

        // Click Sign-in
        WebElement signin = driver.findElement(By.cssSelector("input#signInSubmit"));
        signin.click();
        Thread.sleep(3000);

        // Search for smartphones
        WebElement search = driver.findElement(By.cssSelector("input#twotabsearchtextbox"));
        search.sendKeys("smartphones");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // Apply brand filter (Google)
        WebElement google = driver.findElement(By.cssSelector("li[id*='p_123/370584'] span.a-list-item a span"));
        google.click();
        Thread.sleep(3000);

        // Apply storage filter (128 GB)
        WebElement gb = driver.findElement(By.cssSelector("li[id*='p_n_feature_thirty-two_browse-bin/108501313011'] span.a-list-item a span"));
        gb.click();
        Thread.sleep(3000);

        // Apply battery filter
        WebElement battery = driver.findElement(By.cssSelector("li[id*='p_n_feature_forty-three_browse-bin/120185112011'] span.a-list-item a span"));
        battery.click();
        Thread.sleep(3000);

        // Open account menu
        WebElement select = driver.findElement(By.cssSelector("a#nav-hamburger-menu"));
        select.click();
        Thread.sleep(3000);

        // Sign out
        WebElement signOut = driver.findElement(By.cssSelector("ul.hmenu li:nth-child(26) a"));
        signOut.click();
        Thread.sleep(10000);

        // Get phone name text
        WebElement phonename = driver.findElement(By.cssSelector("div[data-component-type='s-search-result'] h1 span.a-size-large"));
        String ok = phonename.getText();
        System.out.println(ok);

        // Quit browser
        driver.quit();
    }
}
