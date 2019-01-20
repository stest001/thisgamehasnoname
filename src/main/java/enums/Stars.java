package enums;

public enum Stars {
    ONE("1"), TWO("2"), THREE("3"), FOUR("3"), FIVE("3");
    private String value;

    Stars(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
