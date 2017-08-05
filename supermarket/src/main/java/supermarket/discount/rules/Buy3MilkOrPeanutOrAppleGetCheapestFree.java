package supermarket.discount.rules;


import supermarket.shoppingitem.Item;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Buy3MilkOrPeanutOrAppleGetCheapestFree extends BuyXGetYFreeDiscount {

	public Buy3MilkOrPeanutOrAppleGetCheapestFree() {
		super(3, 1);
	}

	@Override
	protected void applyDiscountOnSet(List<Item> itemSet) {
		final Comparator<Item> comparator = Comparator.comparing(Item::getPrice);
		Item cheapestItem = itemSet.stream().min(comparator).get();
		cheapestItem.setPrice(0);
	}

	@Override
	protected Boolean filterCriteria(Item item) {
		return Stream.of("Milk", "PeanutButterJelly", "Apple").anyMatch(str ->
				(str.equals(item.getName()) && !item.hasDiscountApplied())
		);
	}
}
