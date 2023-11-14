package christmas.controller;

import christmas.service.ChristmasPromotionService;
import christmas.view.input.InputView;
import christmas.view.output.DetailsFormatter;
import christmas.view.output.OutputView;

public class ChristmasPromotionController {
    private final ChristmasPromotionService service;

    public ChristmasPromotionController() {
        service = new ChristmasPromotionService();
    }

    public void run() {
        takeVisitDate();
        takeOrder();
        previewEventBenefits();
    }

    private void takeVisitDate() {
        OutputView.printIntroductionMessage();
        OutputView.printTakeDateMessage();
        service.setVisitDate(InputView.getValidInput(InputView::readVisitDate));
    }

    private void takeOrder() {
        OutputView.printTakeOrderMessage();
        service.setOrder(InputView.getValidInput(InputView::readOrder));
    }

    private void previewEventBenefits() {
        OutputView.printPromotionPreviewMessage(formatVisitDate());
        OutputView.printFormattedOrderMessage(formatOrder());
        OutputView.printFormattedDiscountResultMessage(formatDiscountResult());
    }

    private DetailsFormatter formatVisitDate() {
        return DetailsFormatter.visitDateFormatter(service.getVisitDate());
    }

    private DetailsFormatter formatOrder() {
        return DetailsFormatter.OrderFormatter(service.getOrder());
    }

    private DetailsFormatter formatDiscountResult() {
        return DetailsFormatter.DiscountResultFormatter(service.getPromotionSummary());
    }
}
