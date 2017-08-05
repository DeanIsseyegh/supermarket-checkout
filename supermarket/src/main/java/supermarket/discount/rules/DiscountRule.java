package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DiscountRule {

	public void apply(List<Item> items) {
		List<Item> itemsToApplyDiscountOn = filterDiscountableItems(items);
		applyDiscountOn(itemsToApplyDiscountOn);
	}

	abstract void applyDiscountOn(List<Item> items);

	List<Item> filterDiscountableItems(List<Item> items) {
		return items.stream().filter( item -> filterCriteria(item)).collect(Collectors.toList());
	}

	abstract Boolean filterCriteria(Item item);

}
