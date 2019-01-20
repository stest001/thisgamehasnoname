package pages;

import static base.BaseSteps.*;

import enums.SortBy;
import enums.Stars;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static utils.DriverManager.getDriver;

public class SearchResultsPage extends PageBase {

    @FindBy(xpath = ".//div[@class='s-item-container']//a//span[contains(@class, 'price')]")
    private List<WebElement> priceListElements;
    @FindBy(xpath = ".//form[@id='searchSortForm']/select")
    WebElement sortBySelect;

    public SearchResultsPage setAvgCustomerReviewFilter(final Stars stars) {
        String avgCustomerReviewFilter = ".//a[.//span[text()='%s Stars & Up']]";
        String xpath = String.format(avgCustomerReviewFilter, stars.getValue());
        getDriver().findElement(By.xpath(xpath)).click();
        return this;
    }

    public void clickOnItemLinkByPrice(final Double value) {
        String xpath = ".//div[@class='s-item-container' and "
                + "(.//a//span[contains(@class, 'price') and contains(text(), '%s')])]//a[.//h2]";
        String price = String.format("%.2f", value).replaceAll("\\,", ".");
        xpath = String.format(xpath, price);
        getDriver().findElement(By.xpath(xpath)).click();
        waitingDocumentReadyState();
    }

    public SearchResultsPage selectSortBy(final SortBy sort) {
        waitingDocumentReadyState();
        String value = sort.getValue();
        Select select = new Select(waitingForElementVisible(sortBySelect));
        select.selectByValue(value);
        waitingDocumentReadyState();
        return this;
    }

    public List<Double> getPriceList() {
        List<Double> priceList = new ArrayList<>();
        priceListElements.forEach(price -> {
            String value = price.getText().substring(1);
            priceList.add(Double.valueOf(value));
        });
        return priceList;
    }
}

