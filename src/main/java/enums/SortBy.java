package enums;

public enum SortBy {
    PriceLowToHigh("price-asc-rank"),
    PriceHighToLow("price-desc-rank"),
    AvgCustomerReview("review-rank"),
    NewestArrivals("date-desc-rank");
    private String value;

    SortBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}