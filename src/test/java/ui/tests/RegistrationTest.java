package ui.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.AccountCreationPage;
import pageobjects.AccountPage;
import pageobjects.AuthenticationPage;
import pageobjects.MainPage;
import ui.ConfigProperties;
import ui.UserAccount;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class RegistrationTest {
    public static WebDriver driver;
    public MainPage mainPage;
    public AuthenticationPage authenticationPage;
    public AccountCreationPage accountCreationPage;
    public AccountPage accountPage;
    private static final Logger logger = Logger.getLogger(RegistrationTest.class.getName());

    @BeforeClass
    public static void setup() {
        logger.info("Setup process started");
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromedriver"));
        logger.info("ChromeDriver gets initiated");
        driver = new ChromeDriver();
    }

    @Before
    public void init() {
        logger.info("MainPage initiated");
        mainPage = new MainPage(driver);
        logger.info("AuthenticationPage initiated");
        authenticationPage = new AuthenticationPage(driver);
        logger.info("AccountCreationPage initiated");
        accountCreationPage = new AccountCreationPage(driver);
        logger.info("Account initiated");
        accountPage = new AccountPage(driver);
        logger.info("Setup implicitly wait for each method");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(ConfigProperties.getProperty("mainpage"));
    }

    @Test
    public void testSuccessfulRegistration() {
        logger.info("Email generation");
        String email = "flynn+" + RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
        logger.info("Password generation");
        String password = RandomStringUtils.randomAlphanumeric(10);
        logger.info("Postal code generation");
        String postCode = RandomStringUtils.randomNumeric(5);
        logger.info("Mobile phone generation");
        String mobilePhone = "+" + RandomStringUtils.randomNumeric(10);
        logger.info("Opening main page and clicking Sign in button");
        mainPage.clickSignInButton();
        logger.info("Starting account registration with generated email");
        authenticationPage.startAccountAuth(email);
        logger.info("Account registration");
        accountCreationPage.createAccount(new UserAccount("Johnny", "Flynn",
                password, email, "14", "3", "1983", "South Africa", "Johannesburg",
                "10", postCode, mobilePhone, "Home"));
        logger.info("Account registration verification");
        Assert.assertEquals("Johnny Flynn",
                driver.findElement(By.xpath("//*[contains(@title, 'View my customer account')]")).getText());
        logger.info("Signing out");
        accountPage.signOut();
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Closing browser");
        driver.quit();
    }
}

