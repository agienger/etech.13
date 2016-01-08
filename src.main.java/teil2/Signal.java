package teil2;

import java.util.ArrayList;

/**
 * @author Lion Gienger August 2015
 *
 */
public class Signal {
	/**
	 * gatterList: Liste der Gatter, in welches dieses Signal m�ndet signaName:
	 * die ID des Signals signalValue: der Wert des Signals
	 */
	private String signalName;
	private boolean signalValue;
	private ArrayList<Nand> gatterList;
	private boolean hasChanged;
	private boolean previousValue;

	/**
	 * Constructor for objects of class Signal
	 *
	 * setzt den Namen des Signals - initialisiert die Gatterliste
	 * 
	 * @param name
	 *            : name of the signal
	 */
	public Signal(String name) {
		hasChanged = false;
		previousValue = false;
		setName(name);
		gatterList = new ArrayList<Nand>();
	}

	/**
	 * @return der Wert des Signals
	 */
	public boolean getValue() {
		return signalValue;
	}

	/**
	 * @return der Name des Signals
	 */
	public String getName() {
		return signalName;
	}

	/**
	 * @param name
	 *            Setzt den Namen des Signals
	 */
	public void setName(String name) {
		this.signalName = name;
	}

	/**
	 * Schaltungsaufbau: Diese Methode wird aus dem Gatter (Nand Klasse) beim
	 * Aufbau der Schaltung gerufen. Sie f�gt das jeweilige Gatter der
	 * Gatterlist hinzu.
	 * 
	 * @param gatter
	 *            : Das Gatter das zur Gatterliste zugef�gt wird.
	 */
	public void connectTo(Nand gatter) {
		gatterList.add(gatter);
	}

	/**
	 * An dieser Stelle wird der Wert des Signals ge�ndert. In einer Schleife
	 * �ber die angeschlossenen Gatter wird dann jeweils pro Gatter das
	 * Ausgabesignal berechnet. Die �nderung des Ausgangssignals in den
	 * angeschlossenen Gatter wiederun triggert die weitere Berechnungen
	 * 
	 * @param value
	 */
	public void setValue(boolean value) {
		signalValue = value;
		hasChanged = true;
		// logChanges();
		for (Nand gatter : gatterList) {
			gatter.setOutputValue();
		}
	}

	/**
	 * Pr�ft ob das Signal nQ oder Q ist, wenn ja werden die Werte geschrieben
	 */
	private void logChanges() {
		if (this.getName().equals("Q") || this.getName().equals("nQ")) {
			System.out.println(this.getName() + " = " + this.getValue());
		}
	}

	public boolean hasSteadyState() {
		return hasChanged && (signalValue == previousValue);
	}

}
