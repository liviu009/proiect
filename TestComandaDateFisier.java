package ro.ase.cts.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;
import ro.ase.cts.Comanda;
import ro.ase.cts.Produse.ProduseFactory;

public class TestComandaDateFisier extends TestCase {
	ProduseFactory produs;

	private static double numerar;
	private static BufferedReader reader;

	static ArrayList<TestValues> listValuesOk = new ArrayList<TestValues>();

	Comanda comanda;

	public TestComandaDateFisier(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		System.out
				.println("Pregatire test - creare obiecte de tip ProdusCafea");
		produs = ProduseFactory.getProduseFactory();
		comanda = new Comanda("espresso", 1);
		comanda.numerar = numerar;
	}

	protected void tearDown() throws Exception {
		System.out.println("Terminare test - stergere obiecte");
	}

	static {
		System.out.println("Incarcarea clasei TestComanda");
		try {
			getTestData("date.txt");

			System.out.println("Sold initial:" + numerar);
			System.out.println("Nr seturi valori ok:" + listValuesOk.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testDepundereNumerar() {
		for (TestValues valori : listValuesOk) {
			try {
				comanda.numerar = numerar + valori.inputValue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals("Testare depunere cu valoare ok",
					valori.expectedValue, comanda.numerar, 0.0);
		}
	}

	private static void getTestData(String fileName) throws IOException {
		File inputFile = new File(fileName);
		if (inputFile.exists()) {
			reader = new BufferedReader(new FileReader(inputFile));
			String linieCurenta;
			while ((linieCurenta = reader.readLine()) != null) {
				if (linieCurenta.startsWith("/"))
					continue;
				else {
					System.out.println(linieCurenta);
					if (linieCurenta.startsWith("^")) {
						String[] simboluri = linieCurenta.split(" ");
						switch (simboluri[1]) {
						case "numerar_initial": {
							linieCurenta = reader.readLine();
							numerar = Double.parseDouble(linieCurenta);
							break;
						}
						case "valori_ok": {
							int nrSeturi = Integer.parseInt(simboluri[2]);
							for (int i = 0; i < nrSeturi; i++) {
								linieCurenta = reader.readLine();
								listValuesOk.add(TestValues
										.parseValori(linieCurenta));
							}
							break;
						}
						}
					}
				}
			}
		} else {
			System.out.println("Fisier inexistent!");
		}
	}
}
