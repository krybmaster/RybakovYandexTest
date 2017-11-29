package com.gmail.rybakk1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс страницы авторизации
 * @author Konstantin Rybakov
 */
public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(name = "login")
    private WebElement loginField;

    @FindBy(name = "passwd")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/div/div/div/div[1]/div[2]/div/div/div/form/div/div[2]/button")
    private WebElement loginButton;

    @FindBy(css = ".domik3__mail-promo")
    private WebElement createTheMail;

    /**
     * Ввод логина
     * @param login - логин
     */
    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    /**
     * Ввод пароля
     * @param password - пароль
     */
    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Клик по кнопке входа в учетную запись
     */
    public void clickLoginButton() {
        loginButton.click();
    }

    /**
     * Проверка элемента, свидетельствующего об отсутствии входа в учетную запись
     * @return - элемент с текстом "Завести почту"
     */
    public String getTextAfterLogout() {
        return createTheMail.getText();
    }
}
