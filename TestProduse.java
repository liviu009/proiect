package ro.ase.cts.test;

import ro.ase.cts.Exception.PretProdusException;
import ro.ase.cts.Produse.Cappuccino;
import ro.ase.cts.Produse.CiocolataCalda;
import ro.ase.cts.Produse.Espresso;
import ro.ase.cts.Produse.EspressoDublu;
import ro.ase.cts.Produse.LatteMachiato;
import ro.ase.cts.Produse.ProdusCafea;
import ro.ase.cts.Produse.ProduseFactory;
import junit.framework.TestCase;

public class TestProduse extends TestCase {
	ProduseFactory produs;
	private static String tipProdus;
	ProdusCafea produsCafea;
	Espresso espresso;
	EspressoDublu espressoDublu;
	Cappuccino cappuccino;
	CiocolataCalda ciocolataCalda;
	LatteMachiato latteMachiato;

	public TestProduse(String message) {
		super(message);
	}

	public void setUp() {
		System.out
				.println("Pregatire test - creare obiecte de tip ProdusCafea");
		produs = ProduseFactory.getProduseFactory();
	}

	public void tearDown() {
		System.out.println("Terminare test - stergere obiecte");
	}

	//------test metoda creareInstanta()------
	public void testCreareInstantaEspresso() {
		try {
			tipProdus = "espresso";
			produsCafea = produs.creareInstanta(tipProdus);
			espresso = new Espresso();
			assertEquals(espresso.getClass(), produsCafea.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testCreareInstantaEspressoDublu() {
		try {
			tipProdus = "espresso dublu";
			produsCafea = produs.creareInstanta(tipProdus);
			espressoDublu = new EspressoDublu();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(espressoDublu.getClass(), produsCafea.getClass());
	}

	public void testCreareInstantaCappuccino() {
		try {
			tipProdus = "cappuccino";
			produsCafea = produs.creareInstanta(tipProdus);
			cappuccino = new Cappuccino();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(cappuccino.getClass(), produsCafea.getClass());
	}

	public void testCreareInstantaCiocoladaCalda() {
		try {
			tipProdus = "ciocolata";
			produsCafea = produs.creareInstanta(tipProdus);
			ciocolataCalda = new CiocolataCalda();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(ciocolataCalda.getClass(), produsCafea.getClass());
	}

	public void testCreareInstantaLatteMachiato() {
		try {
			tipProdus = "latte machiato";
			produsCafea = produs.creareInstanta(tipProdus);
			latteMachiato = new LatteMachiato();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(latteMachiato.getClass(), produsCafea.getClass());
	}

	public void testCreareInstantaInexistenta() {
		try {
			tipProdus = "cafea";
			produsCafea = produs.creareInstanta(tipProdus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(produsCafea);
	}
	
	//------END test metoda creareInstanta()------
	
	
	//--------test metoda setPret()---------
	
	public void testSetPret()
	{
		try {
			produsCafea = produs.creareInstanta("espresso");
			produsCafea.setPret(3);
			assertEquals(produsCafea.getPret(), 3, 0.0);
		} catch (PretProdusException e) {
			e.printStackTrace();
		}
	}
	
	public void testSetPretZero()
	{
		try {
			produsCafea = produs.creareInstanta("espresso");
			produsCafea.setPret(0);
			fail("Nu s-a generat eroare pt pret produs = 0");
		} catch (PretProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testSetPretNegativ()
	{
		try {
			produsCafea = produs.creareInstanta("espresso");
			produsCafea.setPret(-3);
			fail("Nu s-a generat eroare pt pret produs < 0");
		} catch (PretProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	public void testSetPretMare()
	{
		try {
			produsCafea = produs.creareInstanta("espresso");
			produsCafea.setPret(50);
			fail("Nu s-a generat eroare pt pret produs mare");
		} catch (PretProdusException e) {
		//	e.printStackTrace();
		}
	}
	
	//--------END test metoda setPret()---------
	
	
}
