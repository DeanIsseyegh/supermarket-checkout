package supermarket.discount.rules;

import org.junit.Before;
import org.junit.Test;
import supermarket.shoppingitem.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Buy3MilkOrPeanutOrAppleGetCheapestFreeTest {

	private Buy3MilkOrPeanutOrAppleGetCheapestFree rule;

	@Before
	public void setup() {
		this.rule = new Buy3MilkOrPeanutOrAppleGetCheapestFree(1);
	}

	@Test
	public void Given_1AppleAnd1MilkAnd1PeanutButter_Then_CheapestIsFree() {
		Milk milk = new Milk();
		Apple apple = new Apple();
		PeanutButterJelly peanutButterJelly = new PeanutButterJelly();
		milk.setPrice(1);
		apple.setPrice(2);
		peanutButterJelly.setPrice(3);
		List<Item> items = Stream.of(milk, apple, peanutButterJelly).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(milk, BigDecimal.ZERO, true);
		assertPriceAndDiscount(apple, new BigDecimal(2), true);
		assertPriceAndDiscount(peanutButterJelly, new BigDecimal(3), true);
	}

	@Test
	public void Given_2ApplesAnd1PeanutButter_Then_CheapestIsFree() {
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		PeanutButterJelly peanutButterJelly = new PeanutButterJelly();
		apple1.setPrice(1);
		apple2.setPrice(1);
		peanutButterJelly.setPrice(3);
		List<Item> items = Stream.of(apple1, apple2, peanutButterJelly).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(apple1, BigDecimal.ZERO, true);
		assertPriceAndDiscount(apple2, new BigDecimal(1), true);
		assertPriceAndDiscount(peanutButterJelly, new BigDecimal(3), true);
	}

	@Test
	public void Given_Only1AppleAnd1PeanutButter_Then_NoDiscountIsApplied() {
		Apple apple = new Apple();
		PeanutButterJelly peanutButterJelly = new PeanutButterJelly();
		apple.setPrice(1);
		peanutButterJelly.setPrice(3);
		List<Item> items = Stream.of(apple, peanutButterJelly).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(apple, new BigDecimal(1), false);
		assertPriceAndDiscount(peanutButterJelly, new BigDecimal(3), false);
	}

	@Test
	public void Given_3ApplesAnd1DummyItem_Then_DiscountNotAppliedOnDummyItem() {
		Apple apple1 = new Apple();
		Apple apple2 = new Apple();
		Apple apple3 = new Apple();
		DummyItem dummyItem = new DummyItem("dummy", 0.5);
		apple1.setPrice(1);
		apple2.setPrice(1);
		apple3.setPrice(1);
		List<Item> items = Stream.of(apple1, dummyItem, apple2, apple3).collect(Collectors.toList());
		rule.apply(items);

		assertPriceAndDiscount(apple1, BigDecimal.ZERO, true);
		assertPriceAndDiscount(apple2, new BigDecimal(1), true);
		assertPriceAndDiscount(apple3, new BigDecimal(1), true);
		assertPriceAndDiscount(dummyItem, new BigDecimal(0.5), false);
	}

	private void assertPriceAndDiscount(Item item, BigDecimal expectedPrice, Boolean hasDiscountApplied) {
		assertThat(item.getPrice(), is(expectedPrice));
		assertThat(item.hasDiscountApplied(), is(hasDiscountApplied));
	}

}