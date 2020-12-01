package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable{
	private static final long serialVersionUID = -1327873282437637171L;
	private Cart cart;
	
	public User() {
		cart = new Cart();
	}
	
	public User(Cart cart) {
		this.cart = cart;
	}

	public void clearCart(){
		cart = new Cart();
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public String getName() {
		return null;
	}

	public CreditCard getCreditCard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LocalDateTime getRenewalDate() {
		return null;
	}
	
	public void setRenewalDate(LocalDateTime renewalDate) {}
}
