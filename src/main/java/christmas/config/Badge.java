package christmas.config;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000);

    private final String name;
    private final int threshold;

    Badge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public int getThreshold() {
        return threshold;
    }

    public static Badge of(int totalDiscountAmount) {
        for (Badge badge : Badge.values()) {
            if (Math.negateExact(totalDiscountAmount) >= badge.getThreshold()) {
                return badge;
            }
        }
        return null;
    }
}
