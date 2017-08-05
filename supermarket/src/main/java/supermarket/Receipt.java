package supermarket;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

	public static final String TITLE = "***DEANS BEANS SHOPPING RECEIPT***\n";

    public String generateReceipt(List<Item> items, String currencySymbol) {
		StringBuilder formattedItems = new StringBuilder();
		formatLineItems(formattedItems, items, currencySymbol);
		BigDecimal totalPrice = calcTotalPrice(items);
        return TITLE + "" + formattedItems + "\nTotal: " + currencySymbol + totalPrice.setScale(2, BigDecimal.ROUND_DOWN);
    }

    private String genLineItem(Item item, String currency) {
		return item.getName() + ": " + currency + item.getPrice().setScale(2, BigDecimal.ROUND_DOWN) + "\n";
	}

	//TODO make functional?
	private void formatLineItems(StringBuilder stringBuilder, List<Item> items, String currencySymbol) {
		items.forEach( item -> stringBuilder.append(genLineItem(item, currencySymbol)));
	}

	private BigDecimal calcTotalPrice(List<Item> items) {
    	return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}


