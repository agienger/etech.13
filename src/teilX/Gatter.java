package teilX;

import teil2.Event;
import teil2.EventQueue;

/**
 * Die abstrakte Klasse Gatter stellt die Superklasse f�r alle m�glichen Formen
 * von Gattern zur Verf�gung.
 * 
 * @author Nils-Team
 * @version 1.02 17.06.06
 */

public abstract class Gatter {
	protected Signal[] eingang;
	private Signal ausgang;
	protected int wZeit;
	protected int MAXDurchlauf = 10;
	protected String art = "Gatter";
	protected boolean lastCalculatedValue = false;
	protected boolean initialisiert = false;

	/**
	 * Konstruktor f�r Objekte der Klasse Gatter. <br>
	 * er legt die Anzahl der Eing�nge am Gatter fest.
	 * 
	 * @param anzEin
	 *            Anzahl der anzulegenden Eing�nge
	 */
	public Gatter(int anzEin) {
		eingang = new Signal[anzEin];
	}

	/**
	 * Konstruktor f�r Objekte der Klasse Gatter. <br>
	 * er legt die Anzahl der Eing�nge am Gatter fest. Und die Zeit, die jedes
	 * einzelne Gatter verz�gert, um den Ausgang zu berechnen.
	 * 
	 * @param anzEin
	 *            Anzahl der anzulegenden Eing�nge
	 * @param waitTime
	 *            WarteZeit oder DelayTime des Gatters
	 */
	public Gatter(int anzEin, int waitTime) {
		this.wZeit = waitTime;
		eingang = new Signal[anzEin];
	}

	/**
	 * Die Methode setInput() legt ein Signal an den Eingang des Gatters an.
	 * 
	 * @param stelle
	 *            die Stelle, an der das Signal im Gatter anliegen soll.
	 * @param s
	 *            das Signal, das angeschlossen werden soll.
	 */
	public void setInput(int stelle, Signal s) {
		s.setVerbindung(this);
		eingang[stelle] = s;
	}

	/**
	 * Die Methode setOutput() legt ein Signal an den Ausgang des Gatters an.
	 * 
	 * @param s
	 *            ein Signal, das den Ausgang dieses Gatters darstellt.
	 */
	public void setOutput(Signal s) {
		ausgang = s;
	}

	/**
	 * Die Methode berechnen() erzeugt ein neues {@link Event}, sofern die
	 * EventQueue schon durch {@link EventQueue#start()} gestartet wurde. <br>
	 * Wenn die EventQueue noch nicht gestartet wurde, wird davon ausgegangen,
	 * dass man sich noch in der Initialisierungsphase der Schaltung befindet.
	 * Das heisst das Gatter wird berechnet und der neue Wert direkt ohne
	 * Verz�gerung an das AusgangsSignal angelegt. <br>
	 * Falls in der Schaltung R�ckkopplungen auftreten, bestimmt
	 * {@link #MAXDurchlauf} wie oft das Gatter sich berechnen soll. (momentan
	 * hardcoded auf 10 Durchl�ufe)
	 * 
	 */
	public void berechnen() {
		if (!initialisiert) {
			initialisiert = true;
			lastCalculatedValue = calcOutput();
			ausgang.setValue(lastCalculatedValue);
		} else {
			if (Event.getEventQueue().isStarted()) {
				if (lastCalculatedValue != calcOutput()) {
					lastCalculatedValue = calcOutput();
					new Event(ausgang, Event.getEventQueue().getRunTime() + wZeit,
							lastCalculatedValue);
				}
			} else { // Initialisierung der Schaltung
				if ( (lastCalculatedValue != calcOutput()) && (MAXDurchlauf > 0) ) {
					MAXDurchlauf--;
					lastCalculatedValue = calcOutput();
					ausgang.setValue(lastCalculatedValue); // f�r findSteadyState() ohne
					// Eventerzeugung
				} else {
					System.out.println("Max erreicht f�r Ausgang: " + ausgang.getName() +
							" value: " + lastCalculatedValue);
				}
			} 
		}
	}

	/**
	 * Die Methode getOutputValue() gibt den Wert des AusgangsSignals zur�ck.
	 * 
	 * @return Wert des Ausgangssignals
	 */
	public boolean getOutputValue() {
		return ausgang.getValue();
	}

	/**
	 * calcOutput() wurde absichtlich abstract erstellt, um die Berechnung den
	 * einzelnen Gatter-Typen zu �berlassen.
	 * 
	 * @return den Wert des ausgangs des jeweiligen Gatters.
	 */
	abstract boolean calcOutput();

	public String getTyp() {
		return art;
	}
}
