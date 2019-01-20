package basket;

import base.BaseSteps;
import enums.SortBy;
import enums.Stars;
import org.junit.jupiter.api.Test;
import pages.BasketPage;
import pages.HomePage;
import pages.ItemPage;
import pages.NewItemsPage;
import pages.SearchResultsPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasketActionsTest extends TestsBase {

    private static final String IN_STOCK = "In stock";
    private static final String SEARCH_STRING_VALUE = "iphone x 256gb";
    private static final String ITEM_ASSERT_MESSAGE = "added Item == selected Item";
    private static final String ASSERT_PRODUCT_AAVAILABILITY_MESSAGE = "Product AAvailability is ";

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private ItemPage itemPage = new ItemPage();
    private NewItemsPage newItemsPage = new NewItemsPage();
    private BasketPage basketPage = new BasketPage();

    @Test
    public void test() {
        List<Double> priceList = searchItems();
        addToCart(priceList);
        checkBasket();
        deleteItem();
    }

    private void checkBasket() {
        newItemsPage.clickOnEditBasketButton();
        assertEquals(basketPage.getProductAvailability(), IN_STOCK, ASSERT_PRODUCT_AAVAILABILITY_MESSAGE + IN_STOCK);
    }

    private void addToCart(List<Double> priceList) {
        Double minPrice = priceList.stream().min(Double::compareTo).get();
        searchResultsPage.clickOnItemLinkByPrice(minPrice);
        String itemImg = itemPage.getItemPreview();
        itemPage.clickOnAddToCart();
        String addedItemImg = newItemsPage.getAddedItemPreview();
        assertEquals(itemImg, addedItemImg, ITEM_ASSERT_MESSAGE);
    }

    private List<Double> searchItems() {
        new HomePage()
                .setSearchStringValue(SEARCH_STRING_VALUE)
                .sendEnter();
        BaseSteps.waitingDocumentReadyState();

        return searchResultsPage
                .setAvgCustomerReviewFilter(Stars.FOUR)
                .selectSortBy(SortBy.AvgCustomerReview)
                .getPriceList();
    }

    private void deleteItem() {
        basketPage.clickDeleteButton();
        assertTrue(basketPage.itemIsDeleted());
    }
}
