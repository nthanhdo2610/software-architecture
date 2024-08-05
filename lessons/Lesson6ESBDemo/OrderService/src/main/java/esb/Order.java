package esb;

public class Order {
	private String orderNumber;
	private double amount;
	private String orderType; // New field for order type

	public Order() {
	}

	public Order(String orderNumber, double amount) {
		super();
		this.orderNumber = orderNumber;
		this.amount = amount;
		this.orderType = "domestic";
	}

	public Order(String orderNumber, double amount, String orderType) {
		super();
		this.orderNumber = orderNumber;
		this.amount = amount;
		this.orderType = orderType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "Order: nr=" + orderNumber + " amount=" + amount + " type=" + orderType;
	}
}
