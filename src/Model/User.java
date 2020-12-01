package Model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -1327873282437637171L;

	private String email;
	
	public User() {
		super();
		email = "bryce.cayanan1@ucalgary.ca";
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
