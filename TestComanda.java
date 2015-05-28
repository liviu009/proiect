package ro.ase.cts.test;

import ro.ase.cts.Comanda;
import ro.ase.cts.Frame1;
import ro.ase.cts.Exception.ComandaException;
import ro.ase.cts.Exception.PlataException;
import ro.ase.cts.Exception.PretComandaException;
import ro.ase.cts.Plata.ModPlata;
import ro.ase.cts.Plata.PlataCard;
import ro.ase.cts.Plata.PlataCash;
import ro.ase.cts.Produse.ProdusCafea;
import ro.ase.cts.Produse.ProduseFactory;
import junit.framework.TestCase;

public class TestComanda extends TestCase {
	ProduseFactory produs;
	ProdusCafea espresso;
	ProdusCafea espressoDublu;
	ProdusCafea cappuccino;
	ProdusCafea ciocolataCalda;
	ProdusCafea latteMachiato;

	Comanda comanda;

	public TestComanda(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		System.out
				.println("Pregatire test - creare obiecte de tip ProdusCafea");
		produs = ProduseFactory.getProduseFactory();
		comanda = new Comanda("espresso", 1);
	}

	protected void tearDown() throws Exception {
		System.out.println("Terminare test - stergere obiecte");
	}

	// ------test metoda costComanda()------

	public void testCostComanda() {
		try {
			float pretExcepted;
			espresso = produs.creareInstanta("espresso");
			pretExcepted = comanda.costComanda(espresso);
			assertEquals(pretExcepted, 3, 0.0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testCostComandaValoareZero() {
		try {
			espresso = produs.creareInstanta("espresso");
			comanda.costComanda(espressoDublu);
			fail("Nu s-a generat eroare pentru pret comanda = 0");
		} catch (PretComandaException e) {
			// e.printStackTrace();
		}
	}

	// ------END test metoda costComanda()------

	// ------test metoda setModPlata()------
	public void testSetModPlataCash() throws PlataException {
		PlataCash cash = new PlataCash();
		comanda.setPlata(cash);
		assertNotNull(comanda.getModPlata());
	}

	public void testSetModPlataCard() throws PlataException {
		PlataCard card = new PlataCard();
		comanda.setPlata(card);
		assertSame(comanda.getModPlata(), card);
	}

	// -------END test metoda setModPlata()----

	// -------test metoda achita()-----

	public void testAchitaModPlataCash() {
		try {
			PlataCash cash = new PlataCash();
			comanda.setPlata(cash);
			comanda.achita();
			assertEquals(comanda.achita().contains("cash"), true);
		} catch (PlataException e) {
			e.printStackTrace();
		}
	}

	public void testAchitaModPlataCard() {
		try {
			PlataCard card = new PlataCard();
			comanda.setPlata(card);
			comanda.achita();
			assertSame(comanda.getModPlata(), card);
		} catch (PlataException e) {
			e.printStackTrace();
		}
	}

	public void testAchitaModPlataInexistent() {
		try {
			ModPlata plataRate = null;
			comanda.setPlata(plataRate);
			comanda.achita();
			fail("Nu s-a generat eroare");
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	// ------END test metoda achita()------

	// ------test metoda comanda()------
	public void testComanda() {
		try {
			comanda.comanda();
			assertFalse(comanda.comanda().isEmpty());
		} catch (PretComandaException | ComandaException e) {
			e.printStackTrace();
		}
	}

	public void testComandaProdusExistentEspresso() {
		try {
			comanda.comanda();
			assertEquals(comanda.comanda().contains("ESPRESSO"), true);
		} catch (ComandaException | PretComandaException e) {
			e.printStackTrace();
		}
	}

	public void testComandaProdusExistentCappuccino() {
		try {
			comanda = new Comanda("cappuccino", 1);
			comanda.comanda();
			assertEquals(comanda.comanda().contains("CAPPUCCINO"), true);
		} catch (ComandaException | PretComandaException e) {
			e.printStackTrace();
		}
	}

	public void testComandaProdusInexistent() {
		try {
			comanda = new Comanda("cafea mare", 1);
			comanda.comanda();
			fail("Nu s-a generat eroare pentru comanda inexistenta");
		} catch (ComandaException | PretComandaException e) {
			// e.printStackTrace();
		}
	}

	public void testComandaCantitateZero() {
		try {
			comanda = new Comanda("espresso", 0);
			comanda.comanda();
			fail("Nu s-a generar eroare pe cantitate = 0");
		} catch (PretComandaException | ComandaException e) {
		//	e.printStackTrace();
		}

	}

	public void testComandaCantitateNegativa() {
		try {
			comanda = new Comanda("espresso", -2);
			comanda.comanda();
			fail("Nu s-a generat eroare pe cantitate negativa!");
		} catch (PretComandaException | ComandaException e) {
		//	e.printStackTrace();
		}
	}

	public void testComandaCantitateMare() {
		try {
			comanda = new Comanda("espresso", 20);
			comanda.comanda();
			fail("Nu s-a generat eroare pe cantitate mare!");
		} catch (PretComandaException | ComandaException e) {
		//	e.printStackTrace();
		}
	}
	
	// ------END test metoda comanda()------
	
	//------test metoda depunereSumaBanca()------
	 
	public void testDepunereSumaBanca() throws PretComandaException, ComandaException
	{
		try {
			comanda.comanda();
			comanda.depunereSumaBanca(new PlataCard());
			assertEquals(comanda.contBancar.sumaDepusa, comanda.pretComanda,0.0);
		} catch (PlataException e) {
			e.printStackTrace();
		}	
	}
	
	public void testDepunereSumaBancaZero()
	{
		try {
			comanda.contBancar.sumaDepusa=0;
			comanda.depunereSumaBanca(new PlataCard());
			fail("Nu s-a generat eroare pt sumaDepusa = 0!");
		}
		catch(PlataException e){
		//	e.printStackTrace();
		}
	}
	
	public void testDepunereSumaBancaNegativa()
	{
		try {
			comanda.contBancar.sumaDepusa=-3;
			comanda.depunereSumaBanca(new PlataCard());
			fail("Nu s-a generat eroare pt sumaDepusa < 0!");
		}
		catch(PlataException e){
		//	e.printStackTrace();
		}
	}
	
	public void testDepunereSumaBancaMare()
	{
		try {
			comanda.contBancar.sumaDepusa=5000;
			comanda.depunereSumaBanca(new PlataCard());
			fail("Nu s-a generat eroare pt sumaDepusa MARE!");
		}
		catch(PlataException e){
		//	e.printStackTrace();
		}
	}
	
	//-------END test metoda depundereSumaBanca()
	
	//--------test metoda setNumeAutor--------
	
	public void testSetNumeAutor()
	{
		try {
		Frame1 frame1 = new Frame1();
		assertEquals(frame1.getNumeAutor(),"LIVIU COSMIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSetNumeAutorInvalid()
	{
		try {
		Frame1 frame1 = new Frame1();
		frame1.setNumeAutor("Liviu");
		fail("Nu s-a generat eroare");
		} catch (Exception e) {
		//	e.printStackTrace();
		}
	}
	
	//--------END test setNumeAutor()-------
	
	
}


