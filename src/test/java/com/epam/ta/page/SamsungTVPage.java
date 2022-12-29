package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SamsungTVPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://galaxystore.by/catalogs/smartphones";

    @FindBy(xpath = "//div[@class='home-card__top']/div/a[@href='/product/qe75qn90aauxru']")
    private WebElement QE75QN90AButton;

    public SamsungTVPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SamsungTVPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public SamsungTVPage QE75QN90Acheck() throws InterruptedException {
        QE75QN90AButton.click();
        Thread.sleep(1000);
        logger.info("Successfully reached QE75QN90A page");
        return this;
    }
}
