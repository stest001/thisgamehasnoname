package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends PageBase {

    @FindBy(xpath = ".//input[@id='add-to-cart-button']")
    private WebElement addToCartButton;
    @FindBy(xpath = ".//div[@id='altImages']//li[@class='a-spacing-small item imageThumbnail a-declarative']//img")
    private WebElement itemPreviewImg;

    public void clickOnAddToCart() {
        addToCartButton.click();
    }

    public String getItemPreview() {
        return itemPreviewImg.getAttribute("src")
                .replaceAll(".*\\/", "")
                .replaceAll("\\..*", "");
    }
}
