package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SamsungSmartphonePage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://galaxystore.by/catalogs/smartphones";

    @FindBy(xpath = "//*[@href='/product/sm-m127fzkuser']")
    private WebElement m12Button;

    @FindBy(xpath = "//a[@class='g-link gm-800 gm-ff3 preorder']")
    private WebElement m12add150;

    @FindBy(xpath = "//span[@class='PLUS']")
    private WebElement plusButton;

    public SamsungSmartphonePage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SamsungSmartphonePage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public SamsungSmartphonePage m12check() throws InterruptedException {
        m12Button.click();
        Thread.sleep(1000);
        logger.info("Successfully reached M12 page");
        return this;
    }

    public SamsungSmartphonePage add150M12(int count) throws InterruptedException {
        m12Button.click();
        Thread.sleep(1000);
        m12add150.click();
        Thread.sleep(1000);
        for (int i = 0; i < count; ++i){
            plusButton.click();
            Thread.sleep(1000);
        }
        logger.info("Added 150 phones");
        return this;
    }
}
