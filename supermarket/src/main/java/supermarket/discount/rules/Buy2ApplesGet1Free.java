package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public class Buy2ApplesGet1Free extends BuyXGetYFreeDiscount {

	private static final int NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT = 2;
	private static final int NUM_OF_ITEMS_TO_DISCOUNT = 1;

	public Buy2ApplesGet1Free(int priority) {
		super(NUM_OF_ITEMS_REQUIRED_FOR_DISCOUNT, NUM_OF_ITEMS_TO_DISCOUNT, priority);
	}

	@Override
	protected Boolean filterCriteria(Item item) {
		return "Apple".equals(item.getName()) && !item.hasDiscountApplied();
	}

	@Override
	protected void applyDiscountOnSet(List<Item> items) {
		items.get(items.size() - 1).setPrice(0);
	}
}
