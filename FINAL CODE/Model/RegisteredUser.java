package Model;

import java.time.LocalDateTime;

public class RegisteredUser extends User {
	private static final long serialVersionUID = -4469661400478130010L;
	private String name;
	private String email;
	private String password;
	private CreditCard creditCard;
	private String address;
	private LocalDateTime renewalDate;

	public RegisteredUser(String name, String email, String password, Cart cart, CreditCard creditCard, String address, LocalDateTime date) {
		super(cart);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setCreditCard(creditCard);
		this.setAddress(address);
		this.setRenewalDate(date);
	}
	
	@Override
	public String getName() {
		return name;
	}

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

	@Override
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

	@Override
	public LocalDateTime getRenewalDate() {
		return renewalDate;
	}

	@Override
	public void setRenewalDate(LocalDateTime renewalDate) {
		this.renewalDate = renewalDate;
	}
}
