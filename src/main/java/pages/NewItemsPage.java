package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewItemsPage extends PageBase {

    @FindBy(xpath = ".//div[@id='huc-v2-order-row-container']//img")
    private WebElement addedItemImg;
    @FindBy(xpath = ".//a[contains(text(),'Edit basket')]")
    private WebElement editBasketButton;

    public void clickOnEditBasketButton() {
        editBasketButton.click();
    }

    public String getAddedItemPreview() {
        return addedItemImg.getAttribute("src")
                .replaceAll(".*\\/", "")
                .replaceAll("\\..*", "");
    }
}

