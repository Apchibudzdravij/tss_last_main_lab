package com.epam.ta.page;

import com.epam.ta.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage
{
	private final Logger logger = LogManager.getRootLogger();
	private final String PAGE_URL = "https://galaxystore.by/";

	@FindBy(xpath = "//input[@name='name'][@id='name']")
	private WebElement inputLogin;

	@FindBy(xpath = "/html/body/div[7]/div/div/div/div[2]/div[2]/form/div[3]/input")
	private WebElement inputPassword;

	@FindBy(xpath = "//input[@name='password_confirmation'][@id='password_confirmation']")
	private WebElement inputCheckPassword;

	@FindBy(xpath = "//a[@class='btn_auth_modal2']")
	private WebElement buttonSubmit;

	public LoginPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public LoginPage openPage()
	{
		driver.navigate().to(PAGE_URL);
		logger.info("Login page opened");
		return this;
	}

	public MainPage login(User user)
	{
		inputLogin.sendKeys(user.getUsername());
		inputPassword.sendKeys(user.getPassword());
		buttonSubmit.click();
		logger.info("Login performed");
		return new MainPage(driver);
	}
}
