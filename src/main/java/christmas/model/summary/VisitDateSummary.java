package christmas.model.summary;

import christmas.model.VisitDate;

public class VisitDateSummary {
    private static final String PREVIEW_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private String visitDateDetails;

    public VisitDateSummary(VisitDate visitDate) {
        initVisitDateDetails(visitDate);
    }

    public String getVisitDateDetails() {
        return visitDateDetails;
    }

    private void initVisitDateDetails(VisitDate visitDate) {
        this.visitDateDetails = String.format(PREVIEW_MESSAGE,
                visitDate.getDate().getMonthValue(), visitDate.getDate().getDayOfMonth());
    }
}
