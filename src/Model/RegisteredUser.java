package Model;

public class RegisteredUser extends User{
	private String name;
	private String password;
	private Credit credit;
	private Cart cart;
	private CreditCard creditCard;
	private String address;

	public RegisteredUser(String name, String password, Credit credit, Cart cart, CreditCard creditCard, String address) {
		super();
		this.setName(name);
		this.setPassword(password);
		this.setCredit(credit);
		this.setCart(cart);
		this.setCreditCard(creditCard);
		this.setAddress(address);
	}

	public RegisteredUser() {
		super();
		this.setName("Bryce");
		this.setPassword(null);
		this.setCredit(null);
		this.setCart(null);
		this.setCreditCard(null);
		this.setAddress(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Credit getCredit() {
		return credit;
	}

	public void setCredit(Credit credit) {
		this.credit = credit;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
