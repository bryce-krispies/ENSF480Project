package Model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -1327873282437637171L;
	private Cart cart;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
