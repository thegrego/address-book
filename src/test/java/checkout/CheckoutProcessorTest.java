//package checkout;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static checkout.Item.ItemType.A;
//import static checkout.Item.ItemType.C;
//import static checkout.Item.ItemType.D;
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class CheckoutProcessorTest {
//    private CheckoutProcessor checkoutProcessor;
//
//    @Test
//    public void shouldCalculateSinglePrice() {
//        // given
//        checkoutProcessor = new CheckoutProcessor();
//
//        List<Item> checkout = new ArrayList<Item>(){{
//            add(new Item(C, BigDecimal.valueOf(20.0)));
//            add(new Item(D, BigDecimal.valueOf(15.0)));
//            add(new Item(C, BigDecimal.valueOf(20.0)));
//        }};
//
//        BigDecimal expected = BigDecimal.valueOf(55.0);
//
//        // when
//        BigDecimal actual = checkoutProcessor.calculate(checkout);
//
//        // then
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    @Test
//    public void shouldCalculatePriceWithOffer() {
//        // given
//        List<Item> checkout = new ArrayList<Item>(){{
//            add(new Item(A, BigDecimal.valueOf(50.0)));
//            add(new Item(C, BigDecimal.valueOf(20.0)));
//            add(new Item(A, BigDecimal.valueOf(50.0)));
//            add(new Item(A, BigDecimal.valueOf(50.0)));
//            add(new Item(C, BigDecimal.valueOf(20.0)));
//        }};
//
//        Buy3For150 buy3For150 = new Buy3For150();
//
//        List<Item.ItemType> buy3For150Items = new ArrayList<Item.ItemType>(){{
//            add(A);
//        }};
//
//        Map<Offer, List<Item.ItemType>> offers = new HashMap<Offer, List<Item.ItemType>>() {{
//            put(buy3For150, buy3For150Items);
//        }};
//
//        checkoutProcessor = new CheckoutProcessor(offers);
//
//        BigDecimal expected = BigDecimal.valueOf(170.0);
//
//        // when
//        BigDecimal actual = checkoutProcessor.calculate(checkout);
//
//        // then
//        assertThat(actual).isEqualTo(expected);
//    }
//}
