package supermarket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.hamcrest.CoreMatchers.is;

public class ReceiptTest {

	Receipt receipt;
	String currencySymbol = Currency.getInstance("GBP").getSymbol();

	@Before
	public void setUp() throws Exception {
		receipt = new Receipt();
	}

	@Test
	public void Given_OneItem_Then_ReturnAReceiptString() {
		Item item = mockNewItem("Apple", 10.50);
		List<Item> items = Stream.of(item).collect(Collectors.toList());
		String expectedReceipt = Receipt.TITLE + "Apple: £10.50\n\nTotal: £10.50";
		Assert.assertThat(receipt.generateReceipt(items, currencySymbol), is(expectedReceipt));
	}

	@Test
	public void Given_TwoSameItems_Then_ReturnAReceiptString() {
		Item item = mockNewItem("Apple", 10.50);
		Item item2 = mockNewItem("Apple", 10.50);
		List<Item> items = Stream.of(item, item2).collect(Collectors.toList());
		String expectedReceipt = Receipt.TITLE + "Apple: £10.50\nApple: £10.50\n\nTotal: £21.00";
		Assert.assertThat(receipt.generateReceipt(items, currencySymbol), is(expectedReceipt));
	}

	@Test
	public void Given_MultipleDifferentItems_Then_ReturnAReceiptString() {
		List<Item> items = Stream.of(
				mockNewItem("Apple", 10.25),
				mockNewItem("Apple", 10.25),
				mockNewItem("Banana", 5.40),
				mockNewItem("Orange", 3.20)
		).collect(Collectors.toList());
		String expectedReceipt = Receipt.TITLE + "Apple: £10.25\nApple: £10.25\nBanana: £5.40\nOrange: £3.20\n\nTotal: £29.10";
		Assert.assertThat(receipt.generateReceipt(items, currencySymbol), is(expectedReceipt));
	}

	private Item mockNewItem(String name, double price) {
		Item item = mock(Item.class);
		when(item.getName()).thenReturn(name);
		when(item.getPrice()).thenReturn(new BigDecimal(price));
		return item;
	}

}