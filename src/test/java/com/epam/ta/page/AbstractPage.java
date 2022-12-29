package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage
{
	protected WebDriver driver;

	protected abstract AbstractPage openPage();
	protected final int WAIT_TIMEOUT_SECONDS = 10;

	protected AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}

	protected WebElement waitForElementLocatedBy(WebDriver driver, By by) {
		return new WebDriverWait(driver, 20)
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public AbstractPage waitForPageToLoad() {
		new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("")));
		return this;
	}

	public String getPageUrl(){
		return driver.getCurrentUrl();
	}
}
