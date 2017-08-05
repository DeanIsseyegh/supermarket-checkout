package supermarket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import supermarket.exceptions.NoItemsException;
import supermarket.discounts.DiscountService;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

public class CheckoutKioskTest {

    CheckoutKiosk checkoutKiosk;
    DiscountService mockedDiscountService;
    Receipt mockedReceipt;
    Currency currency = Currency.getInstance("GBP");

    @Before
    public void setUp() throws Exception {
        mockedDiscountService = mock(DiscountService.class);
        mockedReceipt = mock(Receipt.class);
        checkoutKiosk = new CheckoutKiosk(mockedDiscountService, mockedReceipt, currency);
    }

    @Test
    public void Given_Items_Then_ApplyDiscounts() throws Exception {
        List<Item> items = mockItems();
        checkoutKiosk.doCheckout(items);
        verify(mockedDiscountService, times(1)).applyDiscount(items);
    }

    @Test
    public void Given_Items_Then_GenerateAReceipt() throws Exception {
        List<Item> items = mockItems();
        String expectedReceiptOutput = "ReceiptOutput";
        when(mockedReceipt.generateReceipt(items, currency.getSymbol())).thenReturn(expectedReceiptOutput);
        Assert.assertThat(checkoutKiosk.doCheckout(items), is(expectedReceiptOutput));
    }

    @Test(expected = NoItemsException.class)
    public void Given_NullItems_Then_ThrowAnException() throws Exception {
        checkoutKiosk.doCheckout(null);
    }

    @Test(expected = NoItemsException.class)
    public void Given_EmptyItems_Then_ThrowAnException() throws Exception {
        checkoutKiosk.doCheckout(new ArrayList<>());
    }

    private List<Item> mockItems() {
        return Stream.of(mock(Item.class)).collect(Collectors.toList());
    }

}