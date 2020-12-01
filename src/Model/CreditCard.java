package Model;

import java.io.Serializable;

public class CreditCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6085296104233230803L;
	private String number;
	private int CVV;
	private String expiryDate;
	private String companyType; 

	public CreditCard() {
		this.setNumber(null);
		this.setCVV(-1);
		this.setExpiryDate(null);
		this.setCompanyType(null);
	}

	public CreditCard(String number, int CVV, String expiryDate, String companyType) {
		this.setNumber(number);
		this.setCVV(CVV);
		this.setExpiryDate(expiryDate);
		this.setCompanyType(companyType);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
}