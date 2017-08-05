package supermarket.discount;

import supermarket.discount.rules.DiscountRule;
import supermarket.shoppingitem.Item;

import java.util.List;

public class DiscountService {

	DiscountFactory factory;

	public DiscountService(DiscountFactory factory) {
		this.factory = factory;
	}

    public void applyDiscount(List<Item> items) {
		List<DiscountRule> discounts = factory.discounts();
		discounts.forEach( discount -> discount.apply(items));
    }
}


