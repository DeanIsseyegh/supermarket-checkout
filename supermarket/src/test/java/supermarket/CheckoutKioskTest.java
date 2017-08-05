package supermarket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import supermarket.exceptions.NoItemsException;
import supermarket.rules.RuleCalculator;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.hamcrest.CoreMatchers.is;

public class CheckoutKioskTest {

    CheckoutKiosk checkoutKiosk;
    RuleCalculator mockedDiscounter;
    Receipt mockedReceipt;
    Currency currency = Currency.getInstance("GBP");

    @Before
    public void setUp() throws Exception {
        mockedDiscounter = mock(RuleCalculator.class);
        mockedReceipt = mock(Receipt.class);
        checkoutKiosk = new CheckoutKiosk(mockedDiscounter, mockedReceipt, currency);
    }

    @Test
    public void Given_Items_Then_ApplyDiscounts() throws Exception {
        List<Item> items = mockItems();
        checkoutKiosk.doCheckout(items);
        Mockito.verify(mockedDiscounter, times(1)).applyDiscount(items);
    }

    @Test
    public void Given_Items_Then_GenerateAReceipt() throws Exception {
        List<Item> items = mockItems();
        String expectedReceiptOutput = "ReceiptOutput";
        Mockito.when(mockedReceipt.generateReceipt(items, currency.getSymbol())).thenReturn(expectedReceiptOutput);
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
        List<Item> items = new ArrayList<>();
        items.add(mock(Item.class));
        return items;
    }

}