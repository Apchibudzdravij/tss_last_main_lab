package com.epam.ta.page;

import com.epam.ta.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SamsungMainPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String BASE_URL = "https://galaxystore.by/";

    @FindBy(xpath = "//button[@id='dUser']")
    private WebElement buttonRegistrationOrAuthCheck;

    @FindBy(xpath = "//a[@class='REG_MODAL_TRIGGER'][@href='#']")
    private WebElement buttonRegistration;

    @FindBy(xpath = "//a[@class='AUTH_MODAL_TRIGGER'][@href='#']")
    private WebElement buttonAuth;

    @FindBy(xpath = "//*[@class='home-categories__item ddl_campaign ddl_campaign_link']/div/div[text()='Смартфоны']/../..")
    private WebElement smartphoneButton;

    @FindBy(xpath = "/html/body/div[6]/main/div[5]/div[1]/div/div/div/div/div[2]/div[2]/div[3]/div/div[2]/a")
    private WebElement tvButton;
    @FindBy(xpath = "/html/body/div[6]/main/div[2]/div/div/div[1]/div/div[3]/div[3]/div[2]/div[2]/div/div/div[2]/div/a[1]")
    private WebElement tvAddButton;
    @FindBy(xpath = "/html/body/div[9]/div/div/div[3]/a")
    private WebElement toKorzinaButton;
    @FindBy(xpath = "/html/body/div[6]/div[4]/section/div/div[2]/div/div[2]/div/div/div/div/div/div/div/table/tbody/tr[4]/td")
    private WebElement sumInKorzina;

    @FindBy(xpath = "//input[@name='name'][@id='name']")
    private WebElement inputLogin;

    @FindBy(xpath = "//input[@name='email'][@id='Email']")
    private WebElement inputEmail;
    @FindBy(xpath = "//input[@name='email'][@id='email']")
    private WebElement inputEmailForAuth;

    @FindBy(xpath = "/html/body/div[7]/div/div/div/div[2]/div[2]/form/div[3]/input")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@name='password_confirmation'][@id='password_confirmation']")
    private WebElement inputCheckPassword;

    @FindBy(xpath = "//a[@class='btn_reg_modal2']")
    private WebElement buttonRegSubmit;

    @FindBy(xpath = "//a[@class='btn_auth_modal2']")
    private WebElement buttonAuthSubmit;

    private final By linkLoggedInUserLocator = By.xpath("//input[@name='name'][@id='name']");


    public SamsungMainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SamsungMainPage invokeRegistration(User user) throws InterruptedException {
        buttonRegistrationOrAuthCheck.click();
        Thread.sleep(1000);
        buttonRegistration.click();
        Thread.sleep(1000);
        inputLogin.sendKeys(user.getUsername());
        Thread.sleep(1000);
        inputEmail.sendKeys(user.getEmail());
        Thread.sleep(1000);
        inputPassword.sendKeys(user.getPassword());
        Thread.sleep(1000);
        inputCheckPassword.sendKeys(user.getCheckPassword());
        Thread.sleep(1000);
        buttonRegSubmit.click();
        return this;
    }

    public SamsungSmartphonePage invokeSmartphone() throws InterruptedException {
        smartphoneButton.click();
        Thread.sleep(1000);
        logger.info("Smartphone catalog page invoked");
        return new SamsungSmartphonePage(driver);
    }

    public SamsungTVPage invokeTV() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("TV catalog page invoked");
        return new SamsungTVPage(driver);
    }

    public SamsungMainPage invokeAuth(User user) throws InterruptedException {
        buttonRegistrationOrAuthCheck.click();
        Thread.sleep(1000);
        inputEmailForAuth.sendKeys(user.getEmail());
        Thread.sleep(1000);
        inputPassword.sendKeys(user.getPassword());
        Thread.sleep(1000);
        buttonAuthSubmit.click();
        return this;
    }

    public SamsungMainPage invokeKorzinaWithTV() throws InterruptedException {
        Thread.sleep(1000);
        tvButton.click();
        Thread.sleep(1000);
        tvAddButton.click();
        Thread.sleep(1000);
        toKorzinaButton.click();
        return this;
    }

    public boolean checkTVSum(String sum) throws InterruptedException {
        String actual = sumInKorzina.getText();
        System.out.println(sum + "\n|||\n" + actual);
        return sum.equals(actual);
    }

    @Override
    public SamsungMainPage openPage()
    {
        driver.navigate().to(BASE_URL);
        logger.info("Main page opened");
        return this;
    }

    public String getLoggedInUserName()
    {
        WebElement linkLoggedInUser = new WebDriverWait(driver, 1)
                .until(ExpectedConditions.presenceOfElementLocated(linkLoggedInUserLocator));
        return linkLoggedInUser.getAttribute("content");
    }
}
