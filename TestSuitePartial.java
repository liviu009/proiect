package ro.ase.cts.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSuitePartial extends TestCase {

	public static Test suite() {

		TestSuite suitePartial = new TestSuite();

		//-----testare partiala de metode partiala de metode-----
		suitePartial.addTest(new TestProduse("testCreareInstantaEspresso"));
		suitePartial.addTest(new TestComanda("testAchitaModPlataCash"));
		suitePartial.addTest(new TestComandaDateFisier("testDepundereNumerar"));
		suitePartial.addTest(new TestFacadeMachine("testFabricaProdusGolireStocApa"));
		//----END---testare partiala de metode partiala de metode-----

		return suitePartial;

	}

}
