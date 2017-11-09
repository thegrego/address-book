package checkout;

import java.math.BigDecimal;
import java.util.function.BiFunction;

interface Offer {
    BiFunction<Integer, BigDecimal, BigDecimal> value();
}

class Buy3For150 implements Offer {
    private static final BigDecimal offerPrice = BigDecimal.valueOf(130.0);

    @Override
    public BiFunction<Integer, BigDecimal, BigDecimal> value() {
        BiFunction<Integer, BigDecimal, BigDecimal> function = (quantity, unitPrice) -> {
            BigDecimal offerQuantity = BigDecimal.valueOf(Long.valueOf(quantity / 3));
            BigDecimal outOfofferQuantity = BigDecimal.valueOf(quantity % 3);

            BigDecimal offerValue = offerQuantity.multiply(offerPrice);
            BigDecimal outOfOfferValue = outOfofferQuantity.multiply(unitPrice);

            return offerValue.add(outOfOfferValue);
        };

        return function;
    }
}