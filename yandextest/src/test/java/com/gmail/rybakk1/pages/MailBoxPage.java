package com.gmail.rybakk1.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс страницы просмотра & создания почты
 * @author Konstantin Rybakov
 */
public class MailBoxPage {

    public MailBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[1]/div[2]/div/div/a")
    private WebElement btnWriteLetter;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[1]/div/div[1]/label/div[3]/div")
    private WebElement inputWhom;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[1]/div/label/div[3]/input")
    private WebElement inputTopic;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div/div[1]/div[2]/div[2]/label/div[2]/div/div/div/div/div")
    private WebElement txtContent;

    @FindBy(xpath = "//*[@id=\"nb-16\"]")
    private WebElement btnSendLetter;

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div/div/div[1]")
    private WebElement successfullySent;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[2]/div[2]/div/div[1]/a[2]/span")
    private WebElement sentList;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div[1]/div/div/div[2]/div/div[1]/div/div/div/a/div/span[2]/span[2]/span[1]/span[1]/span")
    private WebElement lastSentTopic;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[2]/div[3]/div[2]/div[5]/div[1]/div/div/div[2]/div/div[1]/div/div/div/a/div/span[2]/span[2]/span[2]/span")
    private WebElement lastSentContent;

    @FindBy(xpath = "//*[@id=\"nb-1\"]/body/div[2]/div[4]/div/div[1]/div[3]/div[5]/div[1]")
    private WebElement userProfile;

    @FindBy(xpath = "/html/body/div[2]/div[4]/div/div[1]/div[3]/div[5]")
    private WebElement userMenu;

    @FindBy(css = "div.b-mail-dropdown__item:nth-child(9) > a:nth-child(1)")
    private WebElement btnLogout;

    /**
     * @return Возвращает имя пользователя, совершившего авторизацию
     */
    public String getUserName() {
        String userName = userProfile.getText();
        return userName;
    }

    /**
     * Создание письма
     * @param whom - кому отправить
     * @param topic - тема письма
     * @param content - тело письма
     */
    public void createLetter(String whom, String topic, String content) {
        btnWriteLetter.click();
        inputWhom.sendKeys(whom);
        inputTopic.sendKeys(topic);
        txtContent.sendKeys(content);
    }

    /**
     * Клик по кнопке отправки письма
     */
    public void sendLetter() {
        btnSendLetter.click();
    }

    /**
     * @return Возвращает текст об успешной отправке из элемента страницы
     */
    public String getSuccessfullySent() {
        String s = successfullySent.getText();
        return s;
    }

    /**
     * Клик по вкладке отправленных писем
     */
    public void openSentList() {
        sentList.click();
    }

    /**
     * @return Возвращает тему последнего письма
     */
    public String getLastSentTopic() {
        String s = lastSentTopic.getText();
        return s;
    }

    /**
     * @return Возвращает тело последнего письма
     */
    public String getLastSentContent() {
        String s = lastSentContent.getText();
        return s;
    }

    /**
     * Клик по иконке пользователя, открывающий меню
     */
    public void entryMenu() {
        userMenu.click();
    }

    /**
     * Нажатие кнопки "Выход" в списке меню
     */
    public void userLogout() {
        btnLogout.click();
    }


}
