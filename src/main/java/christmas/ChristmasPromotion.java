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
        controller.setVisitDate(InputView.readVisitDate());

        OutputView.printTakeOrderMessage();
        controller.setOrder(InputView.readOrder());

    }
}
