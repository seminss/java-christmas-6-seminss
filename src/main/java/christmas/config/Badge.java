package christmas.config;

public enum Badge {
    SANTA("산타",20_000),
    TREE("트리",10_000),
    STAR("별",5_000),
    NONE("없음",0);

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
}
