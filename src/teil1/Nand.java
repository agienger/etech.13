package teil1;

/**
 * Class to describe a Nand gatter
 * 
 * @author Lion Gienger
 */
/**
 * @author Lion Gienger August 2015
 *
 */
public class Nand {

	/**
	 * inputSignalArray Ist ein arary von Signalen mit allen Inputsignales dieses Gatters
	 */
	private Signal[] inputSignalArray;
	
	/**
	 * output ist das Ausgangssignal des Gatters
	 */
	private Signal output;

	/**
	 * Konstruktor der Klasse Nand, initialisert ein Signal Array mit anzahl Feldern
	 * @param anzahl
	 */
	public Nand(int anzahl) {
		inputSignalArray = new Signal[anzahl];
	}

	/**
	 * Schaltungsaufbau: Definiert das inputNummer-te Eingangssignal inputSignal. 
	 * Au�erdem weist die Methode diesem Signal dieses Gatter als Ziel zu.
	 * @param inputNummer
	 * @param inputSignal
	 */
	public void setInput(int inputNummer, Signal inputSignal) {
		inputSignalArray[inputNummer] = inputSignal;
		inputSignal.connectTo(this);
	}

	/**
	 * Schaltungsaufbau: Setzt das Ausgangssignal signal
	 * @param s
	 */
	public void setOutput(Signal s) {
		output = s;

	}

	/**
	 * Berechnet den Wert des Ausgangssignals durch Aufruf der Methode @ref calculateNand().
	 * Schreibt die Werte der Endsignale s0 and s1 
	 * 
	 */
	public void setOutputValue() {
		output.setValue(calculateNand());
	}
	
	/**
	 * Dies Methode berechnet das Ausgangssignal des Nand Gatters
	 * In einer Schleife pr�fen wir die Werte der Eingangssignale, falls eines davon false ist, so geben wir true zur�ck. 
	 * Ansonsten geben wir true zur�ck.,
	 * @return the output value
	 */
	private boolean calculateNand() {
		for (int i = 0; i < inputSignalArray.length; i++) {
			if (!inputSignalArray[i].getValue()) {
				return true;
			}
		}
		return false;
	}

	
}
