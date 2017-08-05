package supermarket.discount.rules;

import org.junit.Before;
import org.junit.Test;
import supermarket.shoppingitem.Apple;
import supermarket.shoppingitem.DummyItem;
import supermarket.shoppingitem.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class Buy2ApplesGet1FreeTest {

	private final BigDecimal applePrice = new Apple().getPrice();
	private Buy2ApplesGet1Free rule;

	@Before
	public void setup() {
		this.rule = new Buy2ApplesGet1Free();
	}

	@Test
	public void Given_2Apples_Then_SecondOneIsFree() {
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		List<Item> items = Stream.of(apple1, apple2).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(apple1, applePrice, true);
		assertPriceAndDiscount(apple2, BigDecimal.ZERO, true);
	}

	@Test
	public void Given_3Apples_Then_OnlySecondOneIsFree() {
		List<Item> items = Stream.of(
				new Apple(), new Apple(), new Apple()
		).collect(Collectors.toList());

		rule.apply(items);

		assertPriceAndDiscount(items.get(0), applePrice, true);
		assertPriceAndDiscount(items.get(1), BigDecimal.ZERO, true);
		assertPriceAndDiscount(items.get(2), applePrice, false);
	}

	@Test
	public void Given_1Apple_Then_NoDiscountApplied() {
		List<Item> items = Stream.of(new Apple()).collect(Collectors.toList());

		rule.apply(items);

		assertPriceAndDiscount(items.get(0), applePrice, false);
	}

	@Test
	public void Given_2ApplesThatAreAlreadyDiscounted_Then_NoDiscountApplied() {
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		apple1.markAsDiscounted();
		apple2.markAsDiscounted();
		List<Item> items = Stream.of(apple1, apple2).collect(Collectors.toList());

		rule.apply(items);

		assertPriceAndDiscount(apple1, applePrice, true);
		assertPriceAndDiscount(apple2, applePrice, true);
	}

	@Test
	public void Given_2ApplesWithOneAlreadyDiscounted_Then_NoDiscountApplied() {
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		apple1.markAsDiscounted();
		List<Item> items = Stream.of(apple1, apple2).collect(Collectors.toList());

		rule.apply(items);

		assertPriceAndDiscount(apple1, applePrice, true);
		assertPriceAndDiscount(apple2, applePrice, false);
	}

	@Test
	public void Given_2Apples_AndTwoOtherItems_Then_DiscountOnlyAppliedOnApples() {
		BigDecimal dummyPrice = new BigDecimal(5.00);
		List<Item> items = Stream.of(
				new Apple(), new Apple(), new DummyItem("Dummy", 5.00), new DummyItem("Dummy", 5.00)
		).collect(Collectors.toList());

		rule.apply(items);

		assertPriceAndDiscount(items.get(0), applePrice, true);
		assertPriceAndDiscount(items.get(1), BigDecimal.ZERO, true);

		assertPriceAndDiscount(items.get(2), dummyPrice, false);
		assertPriceAndDiscount(items.get(3), dummyPrice, false);
	}

	private void assertPriceAndDiscount(Item item, BigDecimal expectedPrice, Boolean hasDiscountApplied) {
		assertThat(item.getPrice(), is(expectedPrice));
		assertThat(item.hasDiscountApplied(), is(hasDiscountApplied));
	}

}