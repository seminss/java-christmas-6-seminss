package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class ChristmasPromotion {
    private final ChristmasPromotionController controller;

    public ChristmasPromotion() {
        controller = new ChristmasPromotionController();
    }

    public void run() {
        OutputView.printIntroductionMessage();
        OutputView.printTakeDateMessage();
        while (true) {
            try {
                controller.setVisitDate(InputView.readVisitDate());
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
        OutputView.printTakeOrderMessage();
        while (true) {
            try {
                controller.setOrder(InputView.readOrder());
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
        OutputView.printPromotionPreviewMessage(controller.formatVisitDate());
        OutputView.printFormattedOrderMessage(controller.formatOrder());
        OutputView.printFormattedDiscountResultMessage(controller.formatDiscountResult());
    }
}
