package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(@title, 'Log me out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//*[contains(@title, 'Information')]")
    private WebElement myPersonalInformationButton;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void signOut() {
        signOutButton.click();
    }

    public void openPersonalInfo() {
        myPersonalInformationButton.click();
    }
}
