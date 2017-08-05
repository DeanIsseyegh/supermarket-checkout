package supermarket.shoppingitem;

import java.math.BigDecimal;

public abstract class Item {

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

	public void markAsDiscounted() {
		hasDiscountApplied = true;
	}

	public Boolean hasDiscountApplied() {
		return hasDiscountApplied;
	}
}
