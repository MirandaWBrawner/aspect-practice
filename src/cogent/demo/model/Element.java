package cogent.demo.model;

import javax.xml.crypto.MarshalException;

public class Element {
	
	private int atomicNumber;
	private String chemicalSymbol;
	private String englishName;
	
	public Element() {}

	public Element(int atomicNumber, String chemicalSymbol, String englishName) {
		this.atomicNumber = atomicNumber;
		this.chemicalSymbol = chemicalSymbol;
		this.englishName = englishName;
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public String getChemicalSymbol() {
		return chemicalSymbol;
	}

	public void setChemicalSymbol(String chemicalSymbol) {
		this.chemicalSymbol = chemicalSymbol;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	public String thrower() throws Throwable {
		int option = atomicNumber % 5;
		while (option < 0) {
			option += 5;
		}
		Throwable[] array = {
				new NullPointerException(),
				new OutOfMemoryError(),
				new ClassCastException(),
				new MarshalException(),
				new ArithmeticException()
		};
		throw array[option];
	}
}
