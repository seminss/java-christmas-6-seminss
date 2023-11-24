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

    public void start() {
//        takeVisitDate();
//        takeOrder();
//        previewEventBenefits();
    }

    private void takeVisitDate() {
        run(() -> {
            OutputView.printIntroductionMessage();
            OutputView.printTakeDateMessage();
            InputView.readVisitDate();
        });
    }

    private void takeOrder() {
        run(() -> {
            OutputView.printTakeOrderMessage();
            InputView.readOrder();
        });
    }

    private void previewEventBenefits() {
        OutputView.printMessage(formatVisitDate());
        OutputView.printMessage(formatOrder());
        OutputView.printMessage(formatDiscountResult());
        InputView.readClose();
    }

    private DetailsFormatter formatVisitDate() {
        return DetailsFormatter.visitDateFormatter(service.getVisitDateSummary());
    }

    private DetailsFormatter formatOrder() {
        return DetailsFormatter.OrderFormatter(service.getOrderSummary());
    }

    private DetailsFormatter formatDiscountResult() {
        return DetailsFormatter.DiscountResultFormatter(service.getPromotionSummary());
    }

    private void run(Runnable inputRunnable) {
        while (true) {
            try {
                inputRunnable.run();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printMessage(e.getMessage());
            }
        }
    }
}
