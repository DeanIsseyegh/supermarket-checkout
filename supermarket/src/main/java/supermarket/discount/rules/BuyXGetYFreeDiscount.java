package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public abstract class BuyXGetYFreeDiscount extends DiscountRule {

	protected int xNum;
	protected int yFree;

	public BuyXGetYFreeDiscount(int xNum, int yFree) {
		this.xNum = xNum;
		this.yFree = yFree;
	}

	@Override
	void applyDiscountOn(List<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			if (shouldApply(i)) {
				List<Item> itemSetToApplyDiscount = items.subList(i + 1 - xNum, i + 1);
				applyDiscountOnSet(itemSetToApplyDiscount);
				markItemSetAsDiscounted(itemSetToApplyDiscount);
			}
		}
	}

	private Boolean shouldApply(int index) {
		return (index + 1) % xNum == 0;
	}

	abstract void applyDiscountOnSet(List<Item> itemSet);

	private void markItemSetAsDiscounted(List<Item> items) {
		items.forEach(Item::markAsDiscounted);
	}

}
