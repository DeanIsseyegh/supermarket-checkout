package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

public class Buy2ApplesGet1Free extends BuyXGetYFreeDiscount {

	public Buy2ApplesGet1Free() {
		super(2, 1);
	}

	@Override
	Boolean filterCriteria(Item item) {
		return "Apple".equals(item.getName()) && !item.hasDiscountApplied();
	}

}
