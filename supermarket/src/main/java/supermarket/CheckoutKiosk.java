package supermarket;

import supermarket.exceptions.NoItemsException;
import supermarket.rules.RuleCalculator;

import java.util.Currency;
import java.util.List;

public class CheckoutKiosk {

    RuleCalculator discounter;
    Receipt receipt;
    Currency currency;

    public CheckoutKiosk(RuleCalculator discounter, Receipt receipt, Currency currency) {
        this.discounter = discounter;
        this.receipt = receipt;
        this.currency = currency;
    }

    public String doCheckout(List<Item> items) throws NoItemsException {
        if (items == null || items.isEmpty()) throw new NoItemsException();
        discounter.applyDiscount(items);
        return receipt.generateReceipt(items, currency.getSymbol());
    }

}

