package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverManager.getDriver;

public abstract class PageBase {

    public PageBase() {
        PageFactory.initElements(getDriver(), this);
    }

    public static final int TIME_OUT_IN_SECONDS = 20;

    public WebElement waitingForElementVisible(final WebElement field) {
        (new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS)).until((getDriver) -> {
            try {
                return field.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
        return field;
    }

    public void sendEnter() {
        new Actions(getDriver()).sendKeys(Keys.ENTER).perform();
    }


}
