package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.LoginPage;
import com.epam.ta.page.SamsungMainPage;
import com.epam.ta.page.SamsungSmartphonePage;
import com.epam.ta.page.SamsungTVPage;
import com.epam.ta.service.UserCreator;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UserRegTest extends CommonConditions {
    @Test(description = "Might show smth new")
    public void registerValid() throws InterruptedException {
        User testUser = UserCreator.withCredentialsFromProperty("Time4Death!");
        String loggedInUserName = new SamsungMainPage(driver)
                .openPage()
                .invokeRegistration(testUser)
                .getLoggedInUserName();
        assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
    }

    @Test(description = "Might show smth new")
    public void registerNotValid() throws InterruptedException {
        User testUser = UserCreator.withCredentialsFromProperty("a)");
        String loggedInUserName = new SamsungMainPage(driver)
                .openPage()
                .invokeRegistration(testUser)
                .getLoggedInUserName();
        assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
    }

    @org.testng.annotations.Test(description = "Go to M12 page")
    public void openM12Page() throws InterruptedException {
        SamsungSmartphonePage galaxystoreHomePage =
                new SamsungMainPage(driver).openPage()
                        .invokeSmartphone()
                        .m12check();
        Assertions.assertEquals("https://galaxystore.by/product/sm-m127fzkuser",
                galaxystoreHomePage.getPageUrl());
    }

    @org.testng.annotations.Test(description = "Go to QE75QN90A TV")
    void goToTV() throws InterruptedException {
        SamsungTVPage galaxystoreHomePage =
                new SamsungMainPage(driver).openPage()
                        .invokeTV()
                        .QE75QN90Acheck();
        Assertions.assertEquals(
                "https://galaxystore.by/product/qe75qn90aauxru",
                driver.getCurrentUrl());
    }

    @org.testng.annotations.Test(description = "Add 150 M12 to korzina")
    void add150m12() throws InterruptedException {
        SamsungSmartphonePage galaxystoreHomePage =
                new SamsungMainPage(driver).openPage()
                        .invokeSmartphone()
                        .add150M12(150);
        Assertions.assertEquals(
                "https://galaxystore.by/product/sm-m127fzkuser",
                driver.getCurrentUrl());
    }

    @org.testng.annotations.Test(description = "Add 150 M12 to korzina")
    void add130m12() throws InterruptedException {
        SamsungSmartphonePage galaxystoreHomePage =
                new SamsungMainPage(driver).openPage()
                        .invokeSmartphone()
                        .add150M12(130);
        Assertions.assertEquals(
                "https://galaxystore.by/product/sm-m127fzkuser",
                driver.getCurrentUrl());
    }

    @org.testng.annotations.Test(description = "Might show smth new")
    public void authValid() throws InterruptedException {
        User testUser = UserCreator.withCredentialsFromProperty("Time4Death!");
        String loggedInUserName = new SamsungMainPage(driver)
                .openPage()
                .invokeAuth(testUser)
                .getLoggedInUserName();
        assertThat(loggedInUserName, is(equalTo(testUser.getUsername())));
    }

    @Test(description = "add TV and check sum")
    public void checkTvSum() throws InterruptedException {
        String testSum = "5999.00 руб";
        Assertions.assertTrue(new SamsungMainPage(driver)
                .openPage()
                .invokeKorzinaWithTV()
                .checkTVSum(testSum));
    }
}
