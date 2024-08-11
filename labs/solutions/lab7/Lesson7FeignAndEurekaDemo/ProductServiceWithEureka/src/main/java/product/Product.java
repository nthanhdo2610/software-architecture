package product;

public class Product {
	private String id;
	private String price;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String id, String price) {
		super();
		this.id = id;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
