package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Model.CreditCard;

public class FinancialInstituteManager {

	final static String creditCardDB = "CreditCard.db";
	
	@SuppressWarnings("unchecked")
	public static ArrayList<CreditCard> importCreditCards(){
		File f = new File("CreditCard.db");
		if(!f.exists() && !f.isDirectory()) {//if the file dos not exist yet
			ArrayList<CreditCard> fakeCreditCards = new ArrayList<CreditCard>();
			fakeCreditCards.add(new CreditCard("1234567890123456", 286, 1000));
			fakeCreditCards.add(new CreditCard("1724879283938218", 735, 500000));
			fakeCreditCards.add(new CreditCard("2768102834748917", 198, 300));
			fakeCreditCards.add(new CreditCard("1724879283938218", 735, 5));
			
			writeFile(creditCardDB,fakeCreditCards); //save the fake credit cards to the file.
		}
		return (ArrayList<CreditCard>)readFile(creditCardDB);
	}
	public static void updateCreditCards(ArrayList<CreditCard> creditCards){
		writeFile(creditCardDB, creditCards);
	}
	
	public static Object readFile(String file) {
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
	public static void writeFile(String file, Object out) {
		try {
			ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file, false));
			oOut.writeObject(out);
			oOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
