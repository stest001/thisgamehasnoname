package pages;

import base.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.DriverManager.getDriver;

public class HomePage extends PageBase {

    @FindBy(xpath = "//div[@id='nav-tools']//a[@id='nav-link-yourAccount']")
    private WebElement yourAccountTool;
    @FindBy(xpath = ".//span[text()='Sign in' and(@class='nav-action-inner')]")
    private WebElement signInButton;
    @FindBy(xpath = ".//span[text()='Sign in' and(@class='nav-action-inner')]")
    private List<WebElement> signIn;
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

    public void signOut() {
        moveMouseOnYourAccountTool();
        signOutButton.click();
    }

    public void executeSearh(final String value) {
        setSearchStringValue(value);
        sendEnter();
        BaseSteps.waitingDocumentReadyState();
    }

    public HomePage setSearchStringValue(final String value) {
        searchInput.sendKeys(value);
        return this;
    }

    public boolean isAuthorized() {
        moveMouseOnYourAccountTool();
        return signIn.size() == 0;
    }

}
