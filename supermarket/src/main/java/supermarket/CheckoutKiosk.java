package supermarket;

import supermarket.exception.NoItemsException;
import supermarket.discount.DiscountService;
import supermarket.shoppingitem.Item;

import java.util.Currency;
import java.util.List;

public class CheckoutKiosk {

    private DiscountService discountService;
	private Receipt receipt;
	private Currency currency;

    public CheckoutKiosk(DiscountService discountService, Receipt receipt, Currency currency) {
        this.discountService = discountService;
        this.receipt = receipt;
        this.currency = currency;
    }

    public String doCheckout(List<Item> items) throws NoItemsException {
        if (items == null || items.isEmpty()) throw new NoItemsException();
        discountService.applyDiscount(items);
        return receipt.generateReceipt(items, currency.getSymbol());
    }

}

