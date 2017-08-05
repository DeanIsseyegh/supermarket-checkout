package supermarket.discount.rules;


import supermarket.shoppingitem.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Buy3MilkOrPeanutOrAppleGetCheapestFree extends BuyXGetYFreeDiscount {

	public Buy3MilkOrPeanutOrAppleGetCheapestFree(int priority) {
		super(3, 1, priority);
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
