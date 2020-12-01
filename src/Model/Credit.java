package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Credit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9193299566409503722L;
	private String code;
	private double value;
	private LocalDateTime date;
	
	public Credit(String code, double value, LocalDateTime date) {
		setCode(code);
		setValue(value);
		setDate(date);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public boolean isValid() {
		return LocalDateTime.now().isBefore(this.date);
	}
	
}