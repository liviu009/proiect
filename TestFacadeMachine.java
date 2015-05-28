package ro.ase.cts.test;

import junit.framework.TestCase;
import ro.ase.cts.Comanda;
import ro.ase.cts.FacadeMachine;
import ro.ase.cts.Exception.FabricareProdusException;
import ro.ase.cts.Produse.ProdusCafea;
import ro.ase.cts.Produse.ProduseFactory;

public class TestFacadeMachine extends TestCase {

	ProduseFactory produs;
	ProdusCafea espresso;
	ProdusCafea espressoDublu;
	ProdusCafea cappuccino;
	ProdusCafea ciocolataCalda;
	ProdusCafea latteMachiato;
	FacadeMachine masina;

	Comanda comanda;

	public TestFacadeMachine(String message) {
		super(message);
	}

	protected void setUp() throws Exception {
		System.out
				.println("Pregatire test - creare obiecte de tip ProdusCafea");
		masina = new FacadeMachine();
	}

	protected void tearDown() throws Exception {
		System.out.println("Terminare test - stergere obiecte");
	}
	
	//-------test metoda fabricaProdus()------
	
	public void testFabricaProdus()
	{
		try {
			masina.fabricaProdus("espresso", 1);
		} catch (FabricareProdusException e) {
			e.printStackTrace();
		}
		assertEquals(masina.produse.isEmpty(), false);
	}
	
	public void testFabricaProdusInexistent()
	{
		try {
			masina.fabricaProdus("cafea", 1);
			fail("Nu s-a generat eroare pt produs inexistent!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	
	}
	
	public void testFabricaProdusNrMare(){
		try {
			masina.fabricaProdus("espresso", 12);
			fail("Nu s-a generat eroare pt numar produse mare!!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testFabricaProdusNrZero(){
		try {
			masina.fabricaProdus("espresso", 0);
			fail("Nu s-a generat eroare pt numar produse mare!!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testFabricaProdusGolireStocApa()
	{
		try
		{
			masina.fabricaProdus("espresso dublu", 11);
			fail("Nu s-a generat eroare pt stoc gol!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testFabricaProdusGolireStocCafea()
	{
		try
		{
			masina.fabricaProdus("espresso dublu", 11);
			fail("Nu s-a generat eroare pt stoc gol!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testFabricaProdusGolireStocLapte()
	{
		try
		{
			masina.fabricaProdus("latte machiato", 8);
			fail("Nu s-a generat eroare pt stoc gol!");
		} catch (FabricareProdusException e) {
		//	e.printStackTrace();
		}
	}

	//-------END test metoda fabricaProdus()------

}
