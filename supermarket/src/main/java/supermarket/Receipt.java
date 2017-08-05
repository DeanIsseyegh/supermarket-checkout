package supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {

	public static final String TITLE = "***DEANS BEANS SHOPPING RECEIPT***\n";

    public String generateReceipt(List<Item> items, String currencySymbol) {
		String formattedItems = formatLineItems(items, currencySymbol);
		BigDecimal totalPrice = calcTotalPrice(items);
        return TITLE + "" + formattedItems + "\nTotal: " + currencySymbol + totalPrice.setScale(2, BigDecimal.ROUND_DOWN);
    }

	private String formatLineItems(List<Item> items, String currencySymbol) {
		return items.stream().map(item -> genLineItem(item, currencySymbol)).collect(Collectors.joining());
	}

	private String genLineItem(Item item, String currency) {
		return item.getName() + ": " + currency + item.getPrice().setScale(2, BigDecimal.ROUND_DOWN) + "\n";
	}

	private BigDecimal calcTotalPrice(List<Item> items) {
    	return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}


