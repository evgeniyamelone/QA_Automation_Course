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
import org.openqa.selenium.support.ui.Select;
import pageobjects.AccountCreationPage;
import pageobjects.AccountPage;
import pageobjects.AuthenticationPage;
import pageobjects.MainPage;
import pageobjects.PersonalInformationPage;
import ui.ConfigProperties;
import ui.UserAccount;
import ui.UserAccountResult;
import ui.UserAccountsPool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AuthTest {
    public static WebDriver driver;
    public MainPage mainPage;
    public AuthenticationPage authenticationPage;
    public AccountCreationPage accountCreationPage;
    public AccountPage accountPage;
    public PersonalInformationPage personalInformationPage;
    private static final Logger logger = Logger.getLogger(RegistrationTest.class.getName());
    public Map<String, UserAccount> users = new HashMap<>();
    public UserAccountsPool userAccountsPool = new UserAccountsPool(users);

    @BeforeClass
    public static void setup() {
        logger.info("Setup process started");
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        logger.info("Read user from Json");
    }

    @Before
    public void init() {
        logger.info("MainPage initiated");
        mainPage = new MainPage(driver);
        logger.info("AuthenticationPage initiated");
        authenticationPage = new AuthenticationPage(driver);
        logger.info("Account initiated");
        accountPage = new AccountPage(driver);
        logger.info("AccountCreationPage initiated");
        accountCreationPage = new AccountCreationPage(driver);
        logger.info("PersonalInformationPage initiated");
        personalInformationPage = new PersonalInformationPage(driver);
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

        logger.info("Read User Object to Json");
        UserAccount user = new UserAccount("Johnny", "Flynn", password, email, "14", "3", "1983",
                "South Africa", "Johannesburg",
                "10", postCode, mobilePhone, "Home");
        userAccountsPool.serialize("src/test/resources/accountData.json", user);

        logger.info("Create expected account");
        UserAccountResult expectedAccount =
                new UserAccountResult("Johnny", "Flynn", email, "14", "3", "1983");

        logger.info("Read Json to User Object");
        userAccountsPool.deserialize("src/test/resources/accountData.json");

        logger.info("Get User from User Account Pool");
        UserAccount userPool = userAccountsPool
                .getUser("src/test/resources/accountData.json");

        logger.info("Account registration");
        accountCreationPage.createAccount(userAccountsPool
                .getUser("src/test/resources/accountData.json"));
        logger.info("Signing out");
        accountPage.signOut();
        logger.info("Opening main page and clicking Sign in button");
        mainPage.clickSignInButton();

        logger.info("Sign In");
        authenticationPage.logIn(user.getEmail(), user.getPassword());

        logger.info("Open Personal Info page");
        accountPage.openPersonalInfo();

        logger.info("Create actual account");
        UserAccountResult actualAccount =
                new UserAccountResult(driver.findElement(By.name("firstname")).getAttribute("value"),
                        driver.findElement(By.name("lastname")).getAttribute("value"),
                        driver.findElement(By.name("email")).getAttribute("value"),
                        new Select(driver.findElement(By.id("days"))).getFirstSelectedOption().getAttribute("value").trim(),
                        new Select(driver.findElement(By.id("months"))).getFirstSelectedOption().getAttribute("value").trim(),
                        new Select(driver.findElement(By.id("years"))).getFirstSelectedOption().getAttribute("value").trim());

        logger.info("Verify created account");
        Assert.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void testSuccessfulAccountUpdate() {
        mainPage.clickSignInButton();
        logger.info("Starting account registration with generated email");

        logger.info("Test data generation for account update");
        String updatedEmail = "FlynnUpdated+" + RandomStringUtils.randomAlphanumeric(10) + "@gmail.com";
        String updatedFirstName = "JohnnyUpdated";
        String updatedLastName = "FlynnUpdated";

        UserAccountResult updatedAccount = new UserAccountResult(updatedFirstName, updatedLastName, updatedEmail,
                "26", "10", "1985");

        logger.info("Get User from User Account Pool");
        userAccountsPool.deserialize("src/test/resources/accountData.json");
        UserAccount user = userAccountsPool
                .getUser("src/test/resources/accountData.json");

        logger.info("Sign In");
        mainPage.clickSignInButton();
        authenticationPage.logIn(user.getEmail(), user.getPassword());
        logger.info("Open Personal Info page");
        accountPage.openPersonalInfo();

        personalInformationPage.updateAccount(updatedAccount, user.getPassword());

        Assert.assertTrue("Your personal information has been successfully updated.",
                driver.findElement(By.xpath("//*[contains(@id, 'center_column')]")).getText()
                        .contains("Your personal information has been successfully updated."));

        logger.info("Signing out");
        accountPage.signOut();
        logger.info("Opening main page and clicking Sign in button");
        mainPage.clickSignInButton();
        logger.info("Sign In");
        authenticationPage.logIn(updatedAccount.getEmail(), user.getPassword());
        logger.info("Open Personal Info page");
        accountPage.openPersonalInfo();

        logger.info("Create actual account");
        UserAccountResult actualAccount =
                new UserAccountResult(driver.findElement(By.name("firstname")).getAttribute("value"),
                        driver.findElement(By.name("lastname")).getAttribute("value"),
                        driver.findElement(By.name("email")).getAttribute("value"),
                        new Select(driver.findElement(By.id("days"))).getFirstSelectedOption().getAttribute("value").trim(),
                        new Select(driver.findElement(By.id("months"))).getFirstSelectedOption().getAttribute("value").trim(),
                        new Select(driver.findElement(By.id("years"))).getFirstSelectedOption().getAttribute("value").trim());
        logger.info("Verify created account");

        logger.info("Verify created account");
        Assert.assertEquals(updatedAccount, actualAccount);

        logger.info("Read Updated User Object to Json");
        UserAccount userPool = new UserAccount(updatedFirstName, updatedLastName, user.getPassword(), updatedEmail,
                "26", "10", "1985",
                user.getStreetAddress(), user.getCity(),
                user.getState(), user.getPostCode(), user.getMobilePhone(), user.getAlias());
        userAccountsPool.serialize("src/test/resources/accountData.json", userPool);
        logger.info("Signing out");
        accountPage.signOut();
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Closing browser");
        driver.quit();
    }
}
