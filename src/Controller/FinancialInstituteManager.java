package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.CreditCard;

public class FinancialInstituteManager {
	public FinancialInstituteManager() {
		File f = new File("CreditCard.db");
		if(!f.exists() && !f.isDirectory()) {//if the file dos not exist yet
			ArrayList<CreditCard> fakeCreditCards = new ArrayList<CreditCard>();
			fakeCreditCards.add(new CreditCard("1234567890123456", 286, "11/20", "Credit Union"));
			fakeCreditCards.add(new CreditCard("1724879283938218", 735, "12/22", "Bank"));
			fakeCreditCards.add(new CreditCard("2768102834748917", 198, "04/24", "Bank"));
			fakeCreditCards.add(new CreditCard("1724879283938218", 735, "09/22", "Credit Union"));
			fakeCreditCards.add(new CreditCard("9876543210987654", 198, "03/23", "Bank"));
			fakeCreditCards.add(new CreditCard("4817238947817298", 111, "10/21", "Bank"));
			fakeCreditCards.add(new CreditCard("1872395817289356", 876, "01/21", "Company Card"));
			
			writeFile("CreditCard.db",fakeCreditCards); //save the fake credit cards to the file.
		}
	}

	public boolean validateNumber(CreditCard card) {
		ArrayList<CreditCard> creditCards = importCreditNumbers(); // not optimized, could be a local thing instantiated
																	// on creation of class object.
		for (CreditCard selectedCard : creditCards) {
			if (selectedCard.getNumber().equals(card.getNumber()) && selectedCard.getCVV() == card.getCVV())
				return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<CreditCard> importCreditNumbers() {
		return (ArrayList<CreditCard>) readFile("CreditCard.db");
	}

	public Object readFile(String file) { // same class from DBManager, just used here to let us work with a fake
											// database.
		try {
			ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(file));
			Object out = oIn.readObject();
			oIn.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void writeFile(String file, Object out) {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file, false));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
