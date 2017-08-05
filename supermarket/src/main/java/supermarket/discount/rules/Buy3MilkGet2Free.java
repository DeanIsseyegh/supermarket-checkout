package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public class Buy3MilkGet2Free extends BuyXGetYFreeDiscount {

	private static final int NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT = 3;
	private static final int NUM_OF_ITEMS_TO_DISCOUNT = 2;

	public Buy3MilkGet2Free(int priority) {
		super(NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT, NUM_OF_ITEMS_TO_DISCOUNT, priority);
	}

	@Override
	protected  Boolean filterCriteria(Item item) {
		return "Milk".equals(item.getName()) && !item.hasDiscountApplied();
	}

	@Override
	protected void applyDiscountOnSet(List<Item> items) {
		for (int i = 0; i < numItemsToDiscount; i++) {
			items.get((items.size() - 1) - i).setPrice(0);
		}
	}
}
