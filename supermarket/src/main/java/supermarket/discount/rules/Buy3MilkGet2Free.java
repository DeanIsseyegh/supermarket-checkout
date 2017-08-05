package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public class Buy3MilkGet2Free extends BuyXGetYFreeDiscount {

	public Buy3MilkGet2Free(int priority) {
		super(3, 2, priority);
	}

	@Override
	protected  Boolean filterCriteria(Item item) {
		return "Milk".equals(item.getName()) && !item.hasDiscountApplied();
	}

	@Override
	protected void applyDiscountOnSet(List<Item> items) {
		for (int i = 0; i < yFree; i++) {
			items.get((items.size() - 1) - i).setPrice(0);
		}
	}
}
