package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public LoginPage setEmailValue(final String value) {
        emailInput.sendKeys(value);
        return this;
    }

    public LoginPage setPasswordValue(final String value) {
        passwordInput.sendKeys(value);
        return this;
    }

    public void clickOnSignIn() {
        submitButton.click();
    }
}
