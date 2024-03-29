package Model;

public class RegisteredUser extends User {
	private static final long serialVersionUID = -4469661400478130010L;
	private String name;
	private String email;
	private String password;
	private Cart cart;
	private CreditCard creditCard;
	private String address;

	public RegisteredUser(String name, String email, String password, Cart cart, CreditCard creditCard, String address) {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setCart(cart);
		this.setCreditCard(creditCard);
		this.setAddress(address);
	}

	public RegisteredUser() {
		super();
		this.setName(null);
		this.setEmail(null);
		this.setPassword(null);
		this.setCart(null);
		this.setCreditCard(null);
		this.setAddress(null);
	}

//	public String getName() {
//		return name;
//	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
