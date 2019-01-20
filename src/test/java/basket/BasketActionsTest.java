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

import static org.junit.jupiter.api.Assertions.*;

public class BasketActionsTest extends TestsBase {

    private static final String IN_STOCK = "In stock";
    private static final String SEARCH_STRING_VALUE = "iphone x 256gb";
    private static final String ITEM_MATCHER = ".*Apple iPhone.*X.*256.*";
    private static final String ASSERT_ITEM__MESSAGE = "added Item != selected Item";
    private static final String ASSERT_PRODUCT_AAVAILABILITY_MESSAGE = "Product AAvailability is ";
    private static final String ASSERT_IS_AUTHORIZED_MESSAGE = "Is authorized";

    private SearchResultsPage searchResultsPage = new SearchResultsPage();
    private ItemPage itemPage = new ItemPage();
    private NewItemsPage newItemsPage = new NewItemsPage();
    private BasketPage basketPage = new BasketPage();
    private HomePage homePage = new HomePage();

    @Test
    public void test() {
        homePage.executeSearh(SEARCH_STRING_VALUE);

        searchResultsPage
                .setAvgCustomerReviewFilter(Stars.FOUR)
                .selectSortBy(SortBy.AvgCustomerReview);

        String selectedItemName = searchResultsPage.selectItemMinPrice(ITEM_MATCHER);
        itemPage.clickOnAddToCart();

        String addedItemName = newItemsPage.getAddedItemName();
        assertEquals(
                selectedItemName,
                addedItemName,
                ASSERT_ITEM__MESSAGE);

        newItemsPage.clickOnEditBasketButton();
        assertEquals(
                basketPage.getProductAvailability(),
                IN_STOCK,
                ASSERT_PRODUCT_AAVAILABILITY_MESSAGE + IN_STOCK);

        basketPage.clickDeleteButton();
        assertTrue(basketPage.itemIsDeleted());

        homePage.signOut();

        BaseSteps.goToHome();
        assertFalse(homePage.isAuthorized(), ASSERT_IS_AUTHORIZED_MESSAGE);
    }
}
