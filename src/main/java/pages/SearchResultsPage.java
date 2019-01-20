package pages;

import enums.SortBy;
import enums.Stars;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static base.BaseSteps.waitingDocumentReadyState;
import static utils.DriverManager.getDriver;

public class SearchResultsPage extends PageBase {

    @FindBy(xpath = ".//div[@class='s-item-container']//a//span[contains(@class, 'price')]")
    private List<WebElement> priceListElements;
    @FindBy(xpath = ".//form[@id='searchSortForm']/select")
    WebElement sortBySelect;

    public SearchResultsPage selectSortBy(final SortBy sort) {
        waitingDocumentReadyState();
        String value = sort.getValue();
        Select select = new Select(waitingForElementVisible(sortBySelect));
        select.selectByValue(value);
        waitingDocumentReadyState();
        return this;
    }

    public SearchResultsPage setAvgCustomerReviewFilter(final Stars stars) {
        String avgCustomerReviewFilter = ".//a[.//span[text()='%s Stars & Up']]";
        String xpath = String.format(avgCustomerReviewFilter, stars.getValue());
        getDriver().findElement(By.xpath(xpath)).click();
        return this;
    }

    public String selectItemMinPrice(final String itemNameMatcher) {
        List<WebElement> resultsList = new ArrayList<>();
        getDriver().findElements(By.xpath(".//div[@class='s-item-container' and .//h2]")).forEach(webElement -> {
            if (webElement.findElement(By.xpath(".//h2")).getText().matches(itemNameMatcher)) {
                resultsList.add(webElement);
            }
        });
        Double minPrice = getPriceList(resultsList).stream().min(Double::compareTo).get();
        return clickOnItemLinkByPrice(resultsList, minPrice);
    }

    private String clickOnItemLinkByPrice(final List<WebElement> resultsList, final Double value) {
        String xpath = "./div[.//a//span[contains(@class, 'price') "
                + "and contains(text(), '%s')]]//a[.//h2]";
        String price = String.format("%.2f", value).replaceAll("\\,", ".");
        xpath = String.format(xpath, price);
        final String finalXpath = xpath;
        String itemName = "";
        boolean flg = false;
        for (WebElement webElement : resultsList) {
            List<WebElement> innerWEList = webElement.findElements(By.xpath(finalXpath));
            if (innerWEList.size() > 0) {
                itemName = innerWEList.get(0).getText();
                innerWEList.get(0).click();
                flg = true;
                break;
            }
        }
        if (!flg) {
            throw new NoSuchElementException("Item with price " + price + " not found");
        }
        waitingDocumentReadyState();
        return itemName;
    }

    private List<Double> getPriceList(List<WebElement> resultsList) {
        List<Double> priceList = new ArrayList<>();
        resultsList.forEach(price -> {
            String value = price.findElement(By.xpath(".//a//span[contains(@class, 'price')]")).getText().substring(1);
            priceList.add(Double.valueOf(value));
        });
        return priceList;
    }
}

