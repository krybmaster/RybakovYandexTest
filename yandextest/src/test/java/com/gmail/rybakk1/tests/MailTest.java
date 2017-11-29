package com.gmail.rybakk1.tests;

import com.gmail.rybakk1.pages.LoginPage;
import com.gmail.rybakk1.pages.MailBoxPage;
import com.gmail.rybakk1.utilities.RandomString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Класс тестирования почты yandex
 * @author Konstantin Rybakov
 */
public class MailTest {

    public static final String DRIVER_CHROME_PATH = "E:\\BrowserDrivers\\chromedriver.exe";
    public static final String LOGIN = "testovovtest";
    public static final String PASSWORD = "TestItNow!";
    public static final String LETTER_WHOM = "rybakk1@ya.ru";

    public static WebDriver driver;
    public static LoginPage loginPage;
    public static MailBoxPage mailBoxPage;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", DRIVER_CHROME_PATH);
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mailBoxPage = new MailBoxPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://yandex.ru/");
    }

    /**
     * Проверка авторизации в почте
     */
    @Test
    public void loginTest() {
        loginPage.inputLogin(LOGIN);
        loginPage.inputPassword(PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(LOGIN, mailBoxPage.getUserName());
    }

    /**
     * Проверка создания нового письма с содержанием:
     * а) адресат
     * б) тема письма - рандомные символы
     * в) тело письма - рандомные символы
     */
    @Test(dependsOnMethods = "loginTest")
    public void createLetterTest() {
        RandomString.updateStrings();
        mailBoxPage.createLetter(LETTER_WHOM, RandomString.toTopic, RandomString.toContent);
    }

    /**
     * Проверка отправки письма
     */
    @Test(dependsOnMethods = "createLetterTest")
    public void sendLetterTest() {
        mailBoxPage.sendLetter();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Письмо отправлено.",mailBoxPage.getSuccessfullySent());
    }

    /**
     * Проверка наличия отправленного письма в списке отправленных
     * сравнением текста темы и текста тела письма
     */
    @Test(dependsOnMethods = "sendLetterTest")
    public void checkTheSentTest() {
        mailBoxPage.openSentList();
        Assert.assertEquals(RandomString.toTopic,mailBoxPage.getLastSentTopic());
        Assert.assertEquals(RandomString.toContent,mailBoxPage.getLastSentContent());
    }

    /**
     * Проверка корректности выхода из учетной записи
     */
    @Test(dependsOnMethods = "checkTheSentTest")
    public void logoutTest() {
        mailBoxPage.entryMenu();
        mailBoxPage.userLogout();
        Assert.assertEquals("Завести почту", loginPage.getTextAfterLogout());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
