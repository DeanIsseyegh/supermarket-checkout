package supermarket.discount;

import supermarket.discount.rules.DiscountRule;

import java.util.ArrayList;
import java.util.List;

public class DiscountFactory {

	public List<DiscountRule> discounts() {
		return new ArrayList<>();
	}

}
