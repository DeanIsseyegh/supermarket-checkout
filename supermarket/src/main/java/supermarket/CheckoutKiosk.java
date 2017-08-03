package supermarket;

import java.util.List;

public class CheckoutKiosk {

    Discounter discounter;
    Receipt receipt;

    public CheckoutKiosk(Discounter discounter, Receipt receipt) {
        this.discounter = discounter;
        this.receipt = receipt;
    }

    public String doCheckout(List<Item> items) throws NoItemsException {
        if (items == null || items.isEmpty()) throw new NoItemsException();
        discounter.applyDiscount(items);
        return receipt.generateReceipt(items);
    }

}

