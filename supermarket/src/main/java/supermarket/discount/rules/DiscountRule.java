package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DiscountRule {

	private int priority;

	/**
	 *
	 * @param priority - indicates the priority the discount rule takes over other discount rules.
	 *                    Discount rules will run in order of highest priority to lowest.
	 *                    The larger the number, the higher the priority.
	 */
	public DiscountRule(int priority) {
		this.priority = priority;
	}

	public void apply(List<Item> items) {
		List<Item> itemsToApplyDiscountOn = filterDiscountableItems(items);
		applyDiscountOn(itemsToApplyDiscountOn);
	}

	private List<Item> filterDiscountableItems(List<Item> items) {
		return items.stream().filter( item -> filterCriteria(item)).collect(Collectors.toList());
	}

	abstract Boolean filterCriteria(Item item);

	abstract void applyDiscountOn(List<Item> items);

	public int getPriority() {
		return priority;
	}

}
