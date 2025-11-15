package pageobjects;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ui.UserAccount;

@Getter
@Setter
public class AccountCreationPage {
    private WebDriver driver;

    @FindBy(id = "customer_firstname")
    private WebElement customerFirstNameField;

    @FindBy(id = "customer_lastname")
    private WebElement customerLastNameField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(id = "address1")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "id_state")
    private WebElement stateDropdown;

    @FindBy(id = "postcode")
    private WebElement postCodeField;

    @FindBy(id = "phone_mobile")
    private WebElement mobilePhoneField;

    @FindBy(id = "alias")
    private WebElement aliasField;

    @FindBy(id = "submitAccount")
    private WebElement submitAccountButton;

    public AccountCreationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public UserAccount createAccount(@NonNull UserAccount userForm) {
        customerFirstNameField.sendKeys(userForm.getFirstName());
        customerLastNameField.sendKeys(userForm.getLastName());
        passwordField.sendKeys(userForm.getPassword());
        Select dayStatus = new Select(dayDropdown);
        dayStatus.selectByValue(userForm.getDay());
        Select monthStatus = new Select(monthDropdown);
        monthStatus.selectByValue(userForm.getMonth());
        Select yearStatus = new Select(yearDropdown);
        yearStatus.selectByValue(userForm.getYear());
        addressField.sendKeys(userForm.getStreetAddress());
        cityField.sendKeys(userForm.getCity());
        Select stateStatus = new Select(stateDropdown);
        stateStatus.selectByValue(userForm.getState());
        postCodeField.sendKeys(userForm.getPostCode());
        mobilePhoneField.sendKeys("+" + userForm.getMobilePhone());
        aliasField.sendKeys(userForm.getAlias());
        submitAccountButton.click();

        return new UserAccount(userForm.getFirstName(), userForm.getLastName(), userForm.getPassword(),
                userForm.getEmail(), userForm.getDay(), userForm.getMonth(),userForm.getYear(),
                userForm.getStreetAddress(), userForm.getCity(), userForm.getState(), userForm.getPostCode(),
                userForm.getMobilePhone(), userForm.getAlias());
    }
}
