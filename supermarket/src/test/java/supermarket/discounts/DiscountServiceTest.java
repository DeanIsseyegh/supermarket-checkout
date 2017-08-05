package supermarket.discounts;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import supermarket.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DiscountServiceTest {

	DiscountService service;
	DiscountFactory mockedDiscountFactory;

	@Before
	public void setUp() throws Exception {
		mockedDiscountFactory = mock(DiscountFactory.class);
		service = new DiscountService(mockedDiscountFactory);
	}

	@Test
	public void Given_OneItem_Then_ApplyDiscounts() {
		Discount discount = mock(Discount.class);
		List<Discount> discounts = Stream.of(discount).collect(Collectors.toList());
		List<Item> items = mockItems();
		when(mockedDiscountFactory.discounts()).thenReturn(discounts);
		service.applyDiscount(items);
		verify(discount, Mockito.times(1)).apply(items);
	}

	@Test
	public void Given_MultipleItems_Then_ApplyDiscountsOnAllOfThem() {
		Discount discount1 = mock(Discount.class);
		Discount discount2 = mock(Discount.class);
		Discount discount3 = mock(Discount.class);
		List<Discount> discounts = Stream.of(discount1, discount2, discount3).collect(Collectors.toList());
		List<Item> items = mockItems();

		when(mockedDiscountFactory.discounts()).thenReturn(discounts);
		service.applyDiscount(items);
		verify(discount1, Mockito.times(1)).apply(items);
		verify(discount2, Mockito.times(1)).apply(items);
		verify(discount3, Mockito.times(1)).apply(items);
	}

	private List<Item> mockItems() {
		return Stream.of(mock(Item.class)).collect(Collectors.toList());
	}

}