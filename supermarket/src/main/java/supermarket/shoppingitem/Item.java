package supermarket.shoppingitem;

import java.math.BigDecimal;

public abstract class Item {

	/**
	 * Given more time, I would have made name an enum so we could update the name
	 * of an item and how it would appear on the receipt, without having to affect other bits
	 * of logic.
	 */
	private String name;
	private BigDecimal price;
	private Boolean hasDiscountApplied = false;

	public Item(String name, double price) {
		this.name = name;
		this.price = new BigDecimal(price);
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(double newPrice) {
		price = new BigDecimal(newPrice);
	}

	public void markAsDiscounted() {
		hasDiscountApplied = true;
	}

	public Boolean hasDiscountApplied() {
		return hasDiscountApplied;
	}
}
