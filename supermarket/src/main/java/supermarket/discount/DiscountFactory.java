package supermarket.discount;

import supermarket.discount.rules.Buy2ApplesGet1Free;
import supermarket.discount.rules.Buy3MilkGet2Free;
import supermarket.discount.rules.Buy3MilkOrPeanutOrAppleGetCheapestFree;
import supermarket.discount.rules.DiscountRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscountFactory {

	public List<DiscountRule> discounts() {
		return Stream.of(
				new Buy2ApplesGet1Free(2),
				new Buy3MilkGet2Free(3),
				new Buy3MilkOrPeanutOrAppleGetCheapestFree(1)
		)
		.collect(Collectors.toList());
	}

}
