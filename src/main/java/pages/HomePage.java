package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static utils.DriverManager.getDriver;

public class HomePage extends PageBase {

    @FindBy(xpath = "//div[@id='nav-tools']//a[@id='nav-link-yourAccount']")
    private WebElement yourAccountTool;
    @FindBy(xpath = ".//span[text()='Sign in' and(@class='nav-action-inner')]")
    private WebElement signInButton;
    @FindBy(xpath = ".//span[text()='Sign Out' and(@class='nav-text')]")
    private WebElement signOutButton;
    @FindBy(xpath = ".//div[@class='nav-search-field ']//input")
    private WebElement searchInput;

    public HomePage moveMouseOnYourAccountTool() {
        Actions builder = new Actions(getDriver());
        builder.moveToElement(waitingForElementVisible(yourAccountTool)).build().perform();
        return this;
    }

    public void clickOnSignIn() {
        signInButton.click();
    }

    public void clickOnSignOut() {
        signOutButton.click();
    }

    public HomePage setSearchStringValue(final String value) {
        searchInput.sendKeys(value);
        return this;
    }

}
