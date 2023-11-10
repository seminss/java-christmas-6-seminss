package christmas.constant;

public enum EventSymbol {
    MENU_SEPARATOR(","),
    MENU_CONNECTOR("-");

    private final String value;

    EventSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
