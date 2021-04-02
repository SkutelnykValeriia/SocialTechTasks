package org.example;

import com.codeborne.selenide.Configuration;
import org.example.config.BaseConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.*;

public class Tests {

    private Logger log = LogManager.getLogger(Tests.class);
    private static BaseConfig conf = ConfigFactory.create(BaseConfig.class);

    @BeforeClass
    public void setup() {
        log.info("Setup Driver");
        WebDriverManager.chromedriver().setup();
        Configuration.pageLoadTimeout = conf.pageLoadTimeout();
        Configuration.timeout = conf.waitTimeout();
        Configuration.startMaximized = true;
    }

    @Test
    public void landingAccessibilityByAPI() {
        given().get(conf.baseUrl());

        open(conf.baseUrl());

        String authFormStartXpath = "//*[@id=\"popup-auth-form-container\"]";
        
        WebElement registrationForm = getElement(By.xpath("//*[@id=\"content-fixator\"]/div/section[1]/div/div[2]/div"));
        registrationForm.isEnabled();

        WebElement signInButton = getElement(By.xpath(authFormStartXpath + "/div[1]/button"));
        signInButton.click();

        WebElement userEmail = getElement(By.xpath(authFormStartXpath + "/div[2]/div/div/div[1]/div/form/div[1]/label/div/label/input"));
        userEmail.sendKeys("testertostovic@gmail.com");

        WebElement userPassword = getElement(By.xpath(authFormStartXpath + "/div[2]/div/div/div[1]/div/form/div[2]/label/div/label/input"));
        userPassword.sendKeys("123456");

        WebElement signIn = getElement(By.xpath(authFormStartXpath + "/div[2]/div/div/div[1]/div/form/button"));
        signIn.click();
    }

    @Test
    public void landingTestUI() {
        open(conf.baseUrl());
        sleep(1000);

        String startXpathForWebElementsOnRegistrationForm = "//*[@id=\"content-fixator\"]/div/section[1]/div/div[2]/div";
        String startXpathForWebElementsOnSearchForm = "//*[@id=\"hero-form\"]/div/div[1]";
        String startXpathForWebElementsOnSelectAge = "//*[@id=\"hero-form\"]/div/div[2]";

        WebElement regisrtationForm = getElement(By.xpath(startXpathForWebElementsOnRegistrationForm));
        regisrtationForm.isDisplayed();

        WebElement logo = getElement(By.xpath(startXpathForWebElementsOnRegistrationForm + "/div/span"));
        logo.isDisplayed();

        WebElement headerOnRegistrationForm = getElement(By.xpath(startXpathForWebElementsOnRegistrationForm + "/div/h1"));
        headerOnRegistrationForm.isDisplayed();

        WebElement sloganOnRegistrationForm = getElement(By.xpath(startXpathForWebElementsOnRegistrationForm + "/div/p[2]"));
        sloganOnRegistrationForm.isDisplayed();

        WebElement searchForm = getElement(By.xpath("//*[@id=\"hero-form\"]"));
        searchForm.isDisplayed();

        WebElement currentUserGenderMaleIcon = getElement(By.xpath(startXpathForWebElementsOnSearchForm + "/div[1]/div[1]/label[1]/i"));
        currentUserGenderMaleIcon.isEnabled();
        currentUserGenderMaleIcon.click();

        WebElement currentUserGenderFemaleIcon = getElement(By.xpath(startXpathForWebElementsOnSearchForm + "/div[1]/div[1]/label[2]/i"));
        currentUserGenderFemaleIcon.isEnabled();

        WebElement searchingUserGenderMaleIcon = getElement(By.xpath(startXpathForWebElementsOnSearchForm + "/div[2]/div[1]/label[1]/i"));
        searchingUserGenderMaleIcon.isEnabled();

        WebElement searchingUserGenderFemaleIcon = getElement(By.xpath(startXpathForWebElementsOnSearchForm + "/div[2]/div[1]/label[2]/i"));
        searchingUserGenderFemaleIcon.isEnabled();
        searchingUserGenderFemaleIcon.click();

        WebElement searchingMinAge = getElement(By.xpath(startXpathForWebElementsOnSelectAge + "/span[1]/div/div/select"));
        searchingMinAge.isEnabled();
        searchingMinAge.click();

        WebElement searchingMaxAge = getElement(By.xpath(startXpathForWebElementsOnSelectAge + "/span[2]/div/div/select"));
        searchingMaxAge.isEnabled();
        searchingMaxAge.click();

        WebElement takeAChanceButton = getElement(By.xpath("//*[@id=\"hero-form\"]/div/div[3]/button"));
        takeAChanceButton.isEnabled();

        WebElement signInViaGoogle = getElement(By.xpath("//*[@id=\"hero-form\"]/div/div[4]/div/div/button/span"));
        signInViaGoogle.isEnabled();
    }

    @AfterClass
    public void teardown() {
        log.info("Close Driver");
        closeWebDriver();
    }
}