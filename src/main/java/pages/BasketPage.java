package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends PageBase {

    @FindBy(xpath = ".//span[contains(@class,'sc-product-availability')]")
    private WebElement productAvailability;
    @FindBy(xpath = ".//input[@value='Delete']")
    private WebElement deleteButton;
    @FindBy(xpath = ".//div[@data-action='delete' and not(contains(@style, 'display:none;'))]")
    private WebElement itemDeletedDiv;

    public String getProductAvailability() {
        return productAvailability.getText().trim();
    }

    public BasketPage clickDeleteButton() {
        deleteButton.click();
        return this;
    }

    public boolean itemIsDeleted() {
        boolean isDisplayed = false;
        try {
            isDisplayed = itemDeletedDiv.isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return isDisplayed;
    }
}
