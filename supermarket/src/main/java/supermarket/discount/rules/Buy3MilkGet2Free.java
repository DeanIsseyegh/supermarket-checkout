package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

public class Buy3MilkGet2Free extends BuyXGetYFreeDiscount {

	public Buy3MilkGet2Free() {
		super(3, 2);
	}

	@Override
	Boolean filterCriteria(Item item) {
		return "Milk".equals(item.getName()) && !item.hasDiscountApplied();
	}
}
