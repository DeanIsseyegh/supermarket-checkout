package supermarket.discounts;

import supermarket.Item;

import java.util.List;

public class DiscountService {

	DiscountFactory factory;

	public DiscountService(DiscountFactory factory) {
		this.factory = factory;
	}

    public void applyDiscount(List<Item> items) {
		List<Discount> discounts = factory.discounts();
		discounts.forEach( discount -> discount.apply(items));
    }
}


