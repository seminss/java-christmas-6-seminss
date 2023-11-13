package christmas.service;

import org.junit.jupiter.api.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionServiceTest {
    private final ChristmasPromotionService service = new ChristmasPromotionService();

    @BeforeEach
    public void setUp() {
        List<SimpleEntry<String, Integer>> testOrderData = List.of(
                new SimpleEntry<>("크리스마스파스타", 2),
                new SimpleEntry<>("바비큐립", 1),
                new SimpleEntry<>("샴페인", 3)
        );
        service.createOrder(testOrderData);
    }
}
