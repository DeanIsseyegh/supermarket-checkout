package supermarket.discount.rules;

import supermarket.shoppingitem.Item;

import java.util.List;

public abstract class BuyXGetYFreeDiscount extends DiscountRule {

	private int xNum;
	private int yFree;

	public BuyXGetYFreeDiscount(int xNum, int yFree) {
		this.xNum = xNum;
		this.yFree = yFree;
	}

	@Override
	void applyDiscountOn(List<Item> items) {
		for (int i = 0; i < items.size(); i++) {
			if (shouldApply(i)) {
				applyDiscountOnYItems(items, i);
				markItemSetAsDiscounted(items.subList(0, i + 1));
			}
		}
	}

	private Boolean shouldApply(int index) {
		return (index + 1) % xNum == 0;
	}

	private void applyDiscountOnYItems(List<Item> items, int index) {
		for (int j = 0; j < yFree; j++) {
			items.get(index - j).setPrice(0);
		}
	}

	private void markItemSetAsDiscounted(List<Item> items) {
		items.forEach(Item::markAsDiscounted);
	}

}
