package Model;

public class User {
	Cart cart;
	String email;
	String name;
	String password;
	CreditCard card;
	String address;
	
	public User() {
		
	}
	
	public Cart getCart() {
		return cart;
	}
}
