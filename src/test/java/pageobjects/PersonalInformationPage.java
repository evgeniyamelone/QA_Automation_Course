package pageobjects;

import lombok.NonNull;
import org.openqa.selenium.WebDriver;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ui.UserAccountResult;

public class PersonalInformationPage {
    private WebDriver driver;

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "old_passwd")
    private WebElement passwordField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(name = "submitIdentity")
    private WebElement saveButton;

    public PersonalInformationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public UserAccountResult updateAccount(@NonNull UserAccountResult userAccount, String password) {
        firstNameField.clear();
        firstNameField.sendKeys(userAccount.getFirstName());
        lastNameField.clear();
        lastNameField.sendKeys(userAccount.getLastName());
        emailField.clear();
        emailField.sendKeys(userAccount.getEmail());
        passwordField.sendKeys(password);
        Select dayStatus = new Select(dayDropdown);
        dayStatus.selectByValue(userAccount.getDay());
        Select monthStatus = new Select(monthDropdown);
        monthStatus.selectByValue(userAccount.getMonth());
        Select yearStatus = new Select(yearDropdown);
        yearStatus.selectByValue(userAccount.getYear());
        saveButton.click();

        return new UserAccountResult(userAccount.getFirstName(), userAccount.getLastName(),
                userAccount.getEmail(), userAccount.getDay(),
                userAccount.getMonth(), userAccount.getYear());
    }
}
