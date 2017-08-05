package supermarket.discount;

import org.junit.Test;
import supermarket.discount.rules.Buy2ApplesGet1Free;
import supermarket.discount.rules.Buy3MilkGet2Free;
import supermarket.discount.rules.Buy3MilkOrPeanutOrAppleGetCheapestFree;
import supermarket.discount.rules.DiscountRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class DiscountFactoryTest {

	@Test
	public void ReturnsDiscountRuleSet() throws Exception {
		DiscountFactory discountFactory = new DiscountFactory();
		List<DiscountRule> expectedRuleSet = Stream.of(
				new Buy2ApplesGet1Free(1),
				new Buy3MilkGet2Free(2),
				new Buy3MilkOrPeanutOrAppleGetCheapestFree(3)
		).collect(Collectors.toList());
		List<DiscountRule> actualRuleSet = discountFactory.discounts();

		assertThat(actualRuleSet.size(), is(expectedRuleSet.size()));
	}

}