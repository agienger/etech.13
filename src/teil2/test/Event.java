package teil2.test;

import teil2.Signal;

/**
 * Die Klasse Event stellt Events zur Verf�gung, die automatisch in eine vorher
 * durch {@link #setEventQueue(EventQueue)} zu spezifizierende EventQueue
 * geladen werden.
 * 
 */
public class Event {
	private static EventQueue eventListe;
	private Signal cSignal;
	private int startTime;
	private boolean neuerWert;

	/**
	 * Erzeugt ein neues Event und f�gt es in die EventQueue an der
	 * richtigen Stelle ein.
	 * 
	 * @param signal
	 *            Signal, das ge�ndert werden soll
	 * @param sTime
	 *            Zeitpunkt zu dem die �nderung geschehen soll
	 * @param nWert
	 *            neuer Wert auf den das Signal zum angegebenen Zeitpunkt
	 *            ge�ndert wird
	 */
	public Event(Signal signal, int sTime, boolean nWert) {
		cSignal = signal;
		startTime = sTime;
		neuerWert = nWert;
		eventListe.addEvent(this);
	}

	/**
	 * Diese Methode setzt die EventQueue, die sp�ter von alles Events genutzt
	 * werden soll.
	 * 
	 * @param e
	 *            EventQueue
	 */
	public static void setEventQueue(EventQueue e) {
		eventListe = e;
	}

	/**
	 * Diese Methode gibt die EventQueue zur�ck.
	 * 
	 * @return EventQueue
	 */
	public static EventQueue getEventQueue() {
		return eventListe;
	}

	/**
	 * Die Methode propagate() f�hrt das Event aus.
	 * <P>
	 * Dabei wird das Event aus der EventQueue gel�scht und das
	 * eventbetreffende Signal ge�ndert.
	 */
	public void propagate() {
		if (!eventListe.isStarted()){
			eventListe.start();
		}
		eventListe.removeEvent(this);
		cSignal.setValue(neuerWert);
	}

	/**
	 * Diese Methode gibt den Zeitpunkt, an dem das Event ausgef�hrt werden
	 * soll zur�ck.
	 * 
	 * @return Zeitpunkt als Integer
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Diese Methode gibt das Signal, das ge�ndert werden soll zur�ck.
	 * 
	 * @return Signal
	 */
	public Signal getSignal() {
		return cSignal;
	}

	/**
	 * Diese Methode gibt den Wert zur�ck auf den dieses Event betreffende
	 * Signal ge�ndert werden soll.
	 * 
	 * @return Wert des Signals
	 */
	public boolean getValue() {
		return neuerWert;
	}

	public String toString() {
		return ("Signal " + cSignal.getName() + " wird zum Zeitpunkt "
				+ startTime + " auf " + neuerWert + " ge�ndert");
	}

	public boolean isLater(Event e) {
		return (e.getStartTime() < this.startTime);
	}
	
	public boolean willBeOverwritten(Event e){
		return (this.cSignal.getName().equals(e.getSignal().getName())
				&& this.startTime == e.getStartTime());
	}
}
