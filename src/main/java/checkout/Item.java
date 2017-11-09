package checkout;

import java.math.BigDecimal;

public class Item {
    public enum ItemType {
        A, B, C, D;
    }

    private final ItemType itemType;
    private final BigDecimal price;

    public Item(ItemType itemTypeValue, BigDecimal priceValue) {
        itemType = itemTypeValue;
        price = priceValue;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
