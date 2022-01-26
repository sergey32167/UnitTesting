package core;

public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String name;

    Browsers(String name) {
        this.name = name;
    }

    public static Browsers getBrowser(String name) {
        for (Browsers browsers : values()) {
            if (browsers.name.equalsIgnoreCase(name)) {
                return browsers;
            }
        }
        throw new RuntimeException("Browser not found!!!");
    }
}
