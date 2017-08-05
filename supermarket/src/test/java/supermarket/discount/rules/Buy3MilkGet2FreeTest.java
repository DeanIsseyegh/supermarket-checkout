package supermarket.discount.rules;

import org.junit.Before;
import org.junit.Test;
import supermarket.shoppingitem.Item;
import supermarket.shoppingitem.Milk;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Buy3MilkGet2FreeTest {

	private final BigDecimal milkPrice = new Milk().getPrice();

	private Buy3MilkGet2Free rule;

	@Before
	public void setup() {
		this.rule = new Buy3MilkGet2Free();
	}

	@Test
	public void Given_3Milk_Then_2ndAnd3rdAreFree() {
		List<Item> items = Stream.of(new Milk(), new Milk(), new Milk()).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(items.get(0), milkPrice, true);
		assertPriceAndDiscount(items.get(1), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(2), BigDecimal.ZERO, true);
	}

	@Test
	public void Given_4Milk_Then_Only2ndAnd3rdAreFree() {
		List<Item> items = Stream.of(
				new Milk(), new Milk(), new Milk(), new Milk()
		).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(items.get(0), milkPrice, true);
		assertPriceAndDiscount(items.get(1), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(2), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(3), milkPrice, false);
	}

	@Test
	public void Given_7Milk_Then_4AreFree() {
		List<Item> items = Stream.of(
				new Milk(), new Milk(), new Milk(), new Milk(), new Milk(), new Milk(), new Milk()
		).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(items.get(0), milkPrice, true);
		assertPriceAndDiscount(items.get(1), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(2), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(3), milkPrice, true);
		assertPriceAndDiscount(items.get(4), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(5), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(6), milkPrice, false);
	}

	private void assertPriceAndDiscount(Item item, BigDecimal expectedPrice, Boolean hasDiscountApplied) {
		assertThat(item.getPrice(), is(expectedPrice));
		assertThat(item.hasDiscountApplied(), is(hasDiscountApplied));
	}
}