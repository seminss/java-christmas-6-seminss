package christmas.model.constant;

public enum Badge {
    SANTA("산타", -20_000),
    TREE("트리", -10_000),
    STAR("별", -5_000),
    NONE("없음", 0);

    private final String name;
    private final int threshold;

    Badge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public static Badge of(int totalDiscountAmount) {
        if (SANTA.threshold >= totalDiscountAmount) {
            return SANTA;
        }
        if (TREE.threshold >= totalDiscountAmount) {
            return TREE;
        }
        if (STAR.threshold >= totalDiscountAmount) {
            return STAR;
        }
        return NONE;
    }
}
