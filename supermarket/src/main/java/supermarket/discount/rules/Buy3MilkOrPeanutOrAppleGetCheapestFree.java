package supermarket.discount.rules;


import supermarket.shoppingitem.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Buy3MilkOrPeanutOrAppleGetCheapestFree extends BuyXGetYFreeDiscount {

	private static final int NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT = 3;
	private static final int NUM_OF_ITEMS_TO_DISCOUNT = 1;
	
	public Buy3MilkOrPeanutOrAppleGetCheapestFree(int priority) {
		super(NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT, NUM_OF_ITEMS_TO_DISCOUNT, priority);
	}

	@Override
	protected void applyDiscountOnSet(List<Item> itemSet) {
		final Comparator<Item> comparator = Comparator.comparing(Item::getPrice);
		Item cheapestItem = itemSet.stream().min(comparator).get();
		cheapestItem.setPrice(0);
	}

	@Override
	protected Boolean filterCriteria(Item item) {
		return Stream.of("Milk", "PeanutButterJelly", "Apple").anyMatch(itemName ->
				(itemName.equals(item.getName()) && !item.hasDiscountApplied())
		);
	}
}
