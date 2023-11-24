package christmas.controller;

import christmas.model.summary.PromotionSummary;
import christmas.service.ChristmasPromotionService;
import christmas.view.input.DateRequest;
import christmas.view.input.InputView;
import christmas.view.input.OrderRequest;
import christmas.view.output.DetailsFormatter;
import christmas.view.output.OutputView;

import java.util.function.Supplier;

public class ChristmasPromotionController {
    private final ChristmasPromotionService service;

    public ChristmasPromotionController() {
        service = new ChristmasPromotionService();
    }

    public void start() {
        final DateRequest dateRequest = takeVisitDate();
        final OrderRequest orderRequest = takeOrder();
        showPreviewMessage(service.getPromotionSummary(dateRequest, orderRequest));
        InputView.readClose();
    }

    private DateRequest takeVisitDate() {
        OutputView.printIntroductionMessage();
        OutputView.printTakeDateMessage();
        return getValidRequest(InputView::readVisitDate);
    }

    private OrderRequest takeOrder() {
        OutputView.printTakeOrderMessage();
        return getValidRequest(InputView::readOrder);
    }

    private void showPreviewMessage(PromotionSummary promotionSummary) {
//        OutputView.printMessage(DetailsFormatter.visitDateFormatter(service.getVisitDateSummary(dateRequest)));
//        OutputView.printMessage(DetailsFormatter.OrderFormatter(service.getOrderSummary(orderRequest)));
        OutputView.printMessage(DetailsFormatter.DiscountResultFormatter(promotionSummary));
    }

    private static <T> T getValidRequest(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }
}
