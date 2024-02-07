package christmas.dto.response;

public final class PromotionResponse {
    private final String visitDateDetails;
    private final String orderDetails;
    private final String baseOrderDetails;
    private final String giveawayDetails;
    private final String discountDetails;
    private final String totalDiscountAmountDetails;
    private final String finalPaymentAmountDetails;
    private final String eventBadgeDetails;

    private PromotionResponse(Builder builder) {
        this.visitDateDetails = builder.visitDateDetails;
        this.orderDetails = builder.orderDetails;
        this.baseOrderDetails = builder.baseOrderDetails;
        this.giveawayDetails = builder.giveawayDetails;
        this.discountDetails = builder.discountDetails;
        this.totalDiscountAmountDetails = builder.totalDiscountAmountDetails;
        this.finalPaymentAmountDetails = builder.finalPaymentAmountDetails;
        this.eventBadgeDetails = builder.eventBadgeDetails;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public String getBaseOrderAmount() {
        return baseOrderDetails;
    }

    public String getVisitDateDetails() {
        return visitDateDetails;
    }

    public String getDiscountDetails() {
        return discountDetails;
    }

    public String getGiveawayDetails() {
        return giveawayDetails;
    }

    public String getTotalDiscountAmountDetails() {
        return totalDiscountAmountDetails;
    }

    public String getFinalPaymentAmountDetails() {
        return finalPaymentAmountDetails;
    }

    public String getEventBadgeDetails() {
        return eventBadgeDetails;
    }

    public static class Builder {
        private String visitDateDetails;
        private String orderDetails;
        private String baseOrderDetails;
        private String giveawayDetails;
        private String discountDetails;
        private String totalDiscountAmountDetails;
        private String finalPaymentAmountDetails;
        private String eventBadgeDetails;

        public Builder visitDateDetails(String visitDateDetails) {
            this.visitDateDetails = visitDateDetails;
            return this;
        }

        public Builder orderDetails(String orderDetails) {
            this.orderDetails = orderDetails;
            return this;
        }

        public Builder baseOrderDetails(String baseOrderDetails) {
            this.baseOrderDetails = baseOrderDetails;
            return this;
        }

        public Builder giveawayDetails(String giveawayDetails) {
            this.giveawayDetails = giveawayDetails;
            return this;
        }

        public Builder discountDetails(String discountDetails) {
            this.discountDetails = discountDetails;
            return this;
        }

        public Builder totalDiscountAmountDetails(String totalDiscountAmountDetails) {
            this.totalDiscountAmountDetails = totalDiscountAmountDetails;
            return this;
        }

        public Builder finalPaymentAmountDetails(String finalPaymentAmountDetails) {
            this.finalPaymentAmountDetails = finalPaymentAmountDetails;
            return this;
        }

        public Builder eventBadgeDetails(String eventBadgeDetails) {
            this.eventBadgeDetails = eventBadgeDetails;
            return this;
        }

        public PromotionResponse build() {
            return new PromotionResponse(this);
        }
    }
}
