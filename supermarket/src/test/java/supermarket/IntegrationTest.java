package supermarket;

import org.junit.Assert;
import org.junit.Test;
import supermarket.discount.DiscountFactory;
import supermarket.discount.DiscountService;
import supermarket.shoppingitem.Apple;
import supermarket.shoppingitem.Item;
import supermarket.shoppingitem.Milk;
import supermarket.shoppingitem.PeanutButterJelly;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegrationTest {

	@Test
	public void IntegrationTest() throws Exception {
		DiscountFactory discountFactory = new DiscountFactory();
		DiscountService discountService = new DiscountService(discountFactory);
		Receipt receipt = new Receipt();
		Currency currency = Currency.getInstance("GBP");
		CheckoutKiosk checkoutKiosk = new CheckoutKiosk(discountService, receipt, currency);

		List<Item> items = Stream.of(
				new Apple(),
				new PeanutButterJelly(),
				new Milk(),
				new Milk(),
				new Apple(),
				new PeanutButterJelly()
		).collect(Collectors.toList());
		String receiptOutput = checkoutKiosk.doCheckout(items);
		System.out.print(receiptOutput);

		assertThat(receiptOutput, is(
				"***DEANS BEANS SHOPPING RECEIPT***\n" +
						"Apple: £3.75\n" +
						"Apple: £3.75\n" +
						"Milk: £0.00\n" +
						"Milk: £0.00\n" +
						"PeanutButterJelly: £10.50\n" +
						"PeanutButterJelly: £10.50\n" +
						"\nTotal: £28.50"
		));
	}

}
