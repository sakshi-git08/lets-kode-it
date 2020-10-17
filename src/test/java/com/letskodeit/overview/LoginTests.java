package com.letskodeit.overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\sakshi.aggarwal\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
    }

    @Test
    public void testLogin(){
        driver.findElement(By.xpath("//a[contains(@href,'sign_in')]")).click();
        driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.id("user_email")).sendKeys("test@email.com");
        WebElement passwordElement = driver.findElement(By.id("user_password"));
        passwordElement.clear();
        passwordElement.sendKeys("abcabc");
        driver.findElement(By.name("commit")).click();
        WebElement accountImage = null;// We're putting try-catch block imagine if login fails then we need to handle it.
        try {
            accountImage = driver.findElement(By.className("gravatar"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(accountImage);
    }

}
