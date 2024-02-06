package christmas.config;

public enum EventSymbol {
    MENU_SEPARATOR(","),
    MENU_CONNECTOR("-"),
    DATA_FORMAT("###,###원");

    private final String value;

    EventSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
