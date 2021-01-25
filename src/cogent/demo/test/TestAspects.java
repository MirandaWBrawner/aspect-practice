package cogent.demo.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cogent.demo.model.Element;

public class TestAspects {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Element oxygen = (Element) context.getBean("oxygen");
		String oxygenString = String.format("%s has an atomic number of %d and a chemical symbol of %s.\n",
				oxygen.getEnglishName(), oxygen.getAtomicNumber(), oxygen.getChemicalSymbol());
		System.out.print(oxygenString);
		Element phosphorus = (Element) context.getBean("sulfur");
		phosphorus.setAtomicNumber(15);
		phosphorus.setChemicalSymbol("P");
		phosphorus.setEnglishName("Phosphorus");
		String phosString = String.format("%s has an atomic number of %d and a chemical symbol of %s.\n",
				phosphorus.getEnglishName(), phosphorus.getAtomicNumber(), phosphorus.getChemicalSymbol());
		System.out.println(phosString);
		try {
			phosphorus.thrower();
		} catch (Throwable t) {
			System.out.println(t.getClass() + " has been caught.");
		}
		for (int round = 0; round < phosphorus.getAtomicNumber(); round++) {
			try {
				String result = maybeReturnMaybeThrow();
				//System.out.println(result);
			} catch (RuntimeException exception) {
				//System.out.println("Runtime exception caught.");
			}
		}
		context.close();
	}
	
	public static String maybeReturnMaybeThrow() {
		if (Math.random() < 0.5) {
			return "All Clear";
		} else throw new RuntimeException();
	}
}
