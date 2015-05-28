package ro.ase.cts.test;

import ro.ase.cts.test.TestValues;


public class TestValues {
	public double inputValue;
	public double expectedValue;
	
	public static TestValues parseValori(String linie) {
		String[] valori = linie.split("\t");
		if(valori.length!=2)
		 return null;
		else 
		{
			TestValues value = new TestValues();
			value.inputValue = Double.parseDouble(valori[0]);
			value.expectedValue = Double.parseDouble(valori[1]);
			return value;
		}
		
	}
}

