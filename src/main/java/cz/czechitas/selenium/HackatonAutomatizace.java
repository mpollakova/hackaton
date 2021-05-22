package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class HackatonAutomatizace {

    private static final String URL_APLIKACE = "http://czechitas-datestovani-hackathon.cz/en/";

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void prihlaseniMusiFungovat() {
        prohlizec.navigate().to(URL_APLIKACE);
        WebElement odkazSignIn = prohlizec.findElement(By.xpath("//div[7]/ul/li/a"));
        odkazSignIn.click();

        WebElement polickoEmailAddress = prohlizec.findElement(By.id("email"));
        polickoEmailAddress.sendKeys("petrnovak@gmail.com");
        WebElement polickoPassword = prohlizec.findElement(By.id("passwd"));
        polickoPassword.sendKeys("petulo");
        WebElement tlacitkoSignIn = prohlizec.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        tlacitkoSignIn.click();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/my-account"));
        WebElement uvitaciText = prohlizec.findElement(By.className("info-account"));
        Assertions.assertEquals("Welcome to your account. Here you can manage all of your personal information and orders.", uvitaciText.getText());
    }

    @Test
    public void followingLinksAreDisplayedOnMyAccountPageWhenUserIsLoggedIn() {
        prohlizec.navigate().to(URL_APLIKACE);
        WebElement odkazSignIn = prohlizec.findElement(By.xpath("//div[7]/ul/li/a"));
        odkazSignIn.click();

        WebElement polickoEmailAddress = prohlizec.findElement(By.id("email"));
        polickoEmailAddress.sendKeys("petrnovak@gmail.com");
        WebElement polickoPassword = prohlizec.findElement(By.id("passwd"));
        polickoPassword.sendKeys("petulo");
        WebElement tlacitkoSignIn = prohlizec.findElement(By.xpath("//*[@id=\"SubmitLogin\"]"));
        tlacitkoSignIn.click();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/my-account"));

        WebElement tlacitkoOrderHistoryAndDetails = prohlizec.findElement(By.xpath("//ul/li[2]/a/span"));
        Assertions.assertEquals("ORDER HISTORY AND DETAILS", tlacitkoOrderHistoryAndDetails.getText());
        WebElement tlacitkoMyCreditSlips = prohlizec.findElement(By.xpath("//ul/li[3]/a/span"));
        Assertions.assertEquals("MY CREDIT SLIPS", tlacitkoMyCreditSlips.getText());
        WebElement tlacitkoMyAddresses = prohlizec.findElement(By.xpath("//ul/li[4]/a/span"));
        Assertions.assertEquals("MY ADDRESSES", tlacitkoMyAddresses.getText());
        WebElement tlacitkoMyPersonalInformation = prohlizec.findElement(By.xpath("//ul/li[5]/a/span"));
        Assertions.assertEquals("MY PERSONAL INFORMATION", tlacitkoMyPersonalInformation.getText());

        WebElement tlacitkoHome = prohlizec.findElement(By.xpath("//body/div/div[2]/div/div[2]/div/ul/li/a/span"));
        tlacitkoHome.click();
        Assertions.assertEquals(URL_APLIKACE, prohlizec.getCurrentUrl());
    }


    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
