package Controller;

import Model.User;
import Model.Theatre;

public class TRS {
	User user;
	Theatre theatre;
	DBManager dbMan;
	ViewController viewCont;
	PaymentController payCont;
	
	public TRS(ViewController viewCont) {
		dbMan = new DBManager(theatre); //Load database
		this.viewCont = viewCont;
		payCont = new PaymentController();
	}
	
	
	
	
	
}
