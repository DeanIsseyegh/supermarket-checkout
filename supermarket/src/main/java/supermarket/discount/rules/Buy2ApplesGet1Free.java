package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public class Buy2ApplesGet1Free extends BuyXGetYFreeDiscount {

	public Buy2ApplesGet1Free(int priority) {
		super(2, 1, priority);
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
