package pages;

import base.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static utils.DriverManager.getDriver;

public class NewItemsPage extends PageBase {

    @FindBy(xpath = "//span[contains(@class, 'sc-product-title')]")
    private WebElement addedItem;
    @FindBy(xpath = ".//div[@id='huc-v2-order-row-container']//img")
    private WebElement addedItemImg;
    @FindBy(xpath = ".//a[contains(text(),'Edit basket')]")
    private WebElement editBasketButton;

    public void clickOnEditBasketButton() {
        editBasketButton.click();
    }

    public String getAddedItemName() {
        Actions builder = new Actions(getDriver());
        builder.moveToElement(waitingForElementVisible(addedItemImg)).build().perform();
        BaseSteps.waitingDocumentReadyState();
        return addedItem.getText();
    }
}

