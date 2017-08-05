package supermarket.shoppingitem;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemTest {

	@Test
	public void Returns_Name() throws Exception {
		Item item = new DummyItem("Apple", 10.00);
		assertThat(item.getName(), is("Apple"));
	}

	@Test
	public void Returns_PriceAsBigDecimal() throws Exception {
		Item item = new DummyItem("Apple", 10.00);
		assertThat(item.getPrice(), is(new BigDecimal(10.00)));
	}

	@Test
	public void Given_NoDiscountApplied_Return_False() {
		Item item = new DummyItem("Apple", 10.00);
		assertThat(item.hasDiscountApplied(), is(false));
	}

	@Test
	public void Given_DiscountApplied_Return_True() {
		Item item = new DummyItem("Apple", 10.00);
		item.markAsDiscounted();
		assertThat(item.hasDiscountApplied(), is(true));
	}

}