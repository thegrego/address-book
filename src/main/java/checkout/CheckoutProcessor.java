//package checkout;
//
//import java.math.BigDecimal;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class CheckoutProcessor {
//    private Map<Offer, List<Item.ItemType>> offers;
//
//    public CheckoutProcessor() {
//        offers = Collections.EMPTY_MAP;
//    }
//
//    public CheckoutProcessor(Map<Offer, List<Item.ItemType>> offersMap) {
//        offers = offersMap;
//    }
//
//    public BigDecimal calculate(List<Item> checkout) {
//        Map<Item.ItemType, List<Item>> groupedItems = checkout.stream()
//                .collect(Collectors.groupingBy(Item::getItemType, Collectors.toList()));
//
//        Stream<Map<Boolean, List<Item.ItemType>>> onOfferOrNot = offers.values().stream()
//                .map(offersList -> offersList.stream()
//                    .collect(Collectors.partitioningBy(o -> o.equals(groupedItems.keySet().stream().map(itemType -> itemType)))
//                )
//        );
//
//        List<Item.ItemType> onOffer = onOfferOrNot.map(m -> m.getOrDefault(true, Collections.EMPTY_LIST)).reduce(Collections.EMPTY_LIST, (x, y) -> new ArrayList(y));
//        onOffer.stream().map(itemType -> offers.entrySet().stream()
//                .filter(offer -> offer.getValue().contains(itemType))
//                .map(offer -> offer.value())
//        );
//
////
////                                offer.getKey().value().apply(Integer.valueOf(itemsMap.getValue().size()), itemsMap.getValue().get(0).getPrice()))
////                        .reduce(BigDecimal.ZERO, (x, y) -> x.add(y))
////                )
////                .findFirst().orElse(BigDecimal.ZERO);
////                .reduce(BigDecimal.ZERO, (x, y) -> x);
//
//        return price;
////        return offers.entrySet().stream()
////                .map(map -> map.getKey().value().apply())
////                .reduce(BigDecimal.ZERO, (x, y) -> x.add(y));
//    }
//}
