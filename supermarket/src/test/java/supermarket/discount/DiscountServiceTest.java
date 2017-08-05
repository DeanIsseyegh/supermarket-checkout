package supermarket.discount;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import supermarket.discount.rules.DiscountRule;
import supermarket.shoppingitem.Item;

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
		DiscountRule discount = mock(DiscountRule.class);
		List<DiscountRule> discounts = Stream.of(discount).collect(Collectors.toList());
		List<Item> items = mockItems();
		when(mockedDiscountFactory.discounts()).thenReturn(discounts);
		service.applyDiscount(items);
		verify(discount, Mockito.times(1)).apply(items);
	}

	@Test
	public void Given_MultipleItems_Then_ApplyDiscountsOnAllOfThemInOrderOfPriority() {
		DiscountRule discount1 = mock(DiscountRule.class);
		DiscountRule discount2 = mock(DiscountRule.class);
		DiscountRule discount3 = mock(DiscountRule.class);
		List<DiscountRule> mockedDiscounts = Stream.of(discount1, discount2, discount3).collect(Collectors.toList());
		List<Item> items = mockItems();

		when(discount1.getPriority()).thenReturn(1);
		when(discount2.getPriority()).thenReturn(3);
		when(discount3.getPriority()).thenReturn(2);
		InOrder inOrder = Mockito.inOrder(discount1, discount2, discount3);

		when(mockedDiscountFactory.discounts()).thenReturn(mockedDiscounts);
		service.applyDiscount(items);
		inOrder.verify(discount1, Mockito.times(1)).apply(items);
		inOrder.verify(discount3, Mockito.times(1)).apply(items);
		inOrder.verify(discount2, Mockito.times(1)).apply(items);
		inOrder.verifyNoMoreInteractions();
	}

	private List<Item> mockItems() {
		return Stream.of(mock(Item.class)).collect(Collectors.toList());
	}

}