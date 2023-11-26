package christmas.controller;

import christmas.dto.response.PromotionResponse;
import christmas.service.ChristmasPromotionService;
import christmas.dto.request.DateRequest;
import christmas.view.input.InputView;
import christmas.dto.request.OrderRequest;
import christmas.view.output.OutputPrompt;
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

    private void showPreviewMessage(PromotionResponse promotionResponse) {
        OutputView.printMessage(OutputPrompt.of(promotionResponse));
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
