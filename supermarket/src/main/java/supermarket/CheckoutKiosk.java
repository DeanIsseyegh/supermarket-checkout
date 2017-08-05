package supermarket;

import supermarket.exceptions.NoItemsException;
import supermarket.discounts.DiscountService;

import java.util.Currency;
import java.util.List;

public class CheckoutKiosk {

    DiscountService discountService;
    Receipt receipt;
    Currency currency;

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

