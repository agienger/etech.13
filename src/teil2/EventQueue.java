package teil2;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Die Klasse EventQueue stellt eine Liste dar, die alle Events enth�lt. <br>
 * Ausserdem verwaltet sie eine statische Variable eventTime, die den Zeitablauf
 * der Schaltung darstellt.
 * 
 * @author Nils-Team
 * @version 1.2 17.06.06
 */
public class EventQueue {
	private LinkedList<Event> eventListe;
	private static int eventTimer;
	private boolean started = false;

	/**
	 * Der Konstruktor der Klasse EventQueue.
	 * 
	 */
	public EventQueue() {
		eventTimer = 0;
		eventListe = new LinkedList<Event>();
	}

	public LinkedList<Event> getEventListe() {
		return eventListe;
	}

	/**
	 * Die Methode setTime() setzt die zentrale Zeitvariable. <br>
	 * Dabei achtet sie darauf, dass die Zeit nicht auf einen fr�heren Zeitpunkt
	 * gesetzt wird.
	 * 
	 * @param nTime
	 *            neue Zeit als Integer
	 * @throws RuntimeException -
	 *             wenn neuer Zeitpunkt vor aktueller Zeit ist.
	 */
	private void setTime(int nTime) throws RuntimeException {
		eventTimer = nTime;
	}

	/**
	 * Die Methode addEvent() sortiert ein Event in die EventQueue ein <br>
	 * Neue Events werden dabei hinter andere Events mit gleicher Zeit
	 * einsortiert. Ausserdem werden Events, die zur gleichen Zeit dasselbe
	 * Signal betreffen �berschrieben.
	 * 
	 * @param e
	 *            das einzusortierende Event
	 */
	public void addEvent(Event e) {
		ListIterator<Event> iterator = eventListe.listIterator();
		while (true){
			if (!iterator.hasNext()){
				eventListe.add(e);
				return;
			}
			Event checkedEvent = iterator.next();
			if (checkedEvent.willBeOverwritten(e)){
				iterator.add(e);
				iterator.previous();
				eventListe.remove(checkedEvent);
				return;
			}
			if (checkedEvent.isLater(e)) {
				iterator.previous();
				iterator.add(e);
                return;
            }
		}
	}
	
	

	/**
	 * Die Methode removeEvent() l�scht ein Event aus der EventQueue.
	 * 
	 * @param e
	 *            zu l�schendes Event
	 */
	public void removeEvent(Event e) {
		if (eventTimer < e.getStartTime()) {
			setTime(e.getStartTime()); // zeit iterieren
		}
		eventListe.remove(e);
	}

	/**
	 * Die Methode hasMore() pr�ft, ob in der EventQueue noch Elemente enthalten
	 * sind.
	 * 
	 * @return true, wenn noch Elemente in der Liste stehen; ansonsten false.
	 */
	public boolean hasMore() {
		return !eventListe.isEmpty();
	}

	/**
	 * Die Methode getFirst() gibt das erste Event der EventQueue als
	 * {@link Event} zur�ck.
	 * 
	 * @return erster Event
	 */
	public Event getFirst() {
		return (Event) eventListe.getFirst();
	}

	/**
	 * Die Methode getRunTime() gibt die aktuelle Zeit der Schaltung zur�ck.
	 * 
	 * @return zentrale Schaltungszeit als Integer
	 */
	public int getRunTime() {
		return eventTimer;
	}

	/**
	 * Die Methode isStarted() pr�ft, ob die EventQueue schon gestartet wurde.
	 * <br>
	 * Die EventQueue wird durch {@link #start()} in {@link Event#propagate()}
	 * gestartet.
	 * 
	 * @return true, wenn Initialisierungsphase abgeschlossen; ansonsten false
	 */
	public boolean isStarted() {
		return started;
	}
	
	public String toString(){
		String output = "";
		ListIterator<Event> iterator = eventListe.listIterator();
		while (iterator.hasNext()){
			Event e = iterator.next();
			output += e.getStartTime() + ":" + e.getSignal().getName()+ ", ";
		}
		return output;
	}

	/**
	 * Die Methode start() startet die EventQueue. <br>
	 * Sie stellt das Ende der Initialisierungsphase (findSteadyState() in
	 * Teilaufgabe2) dar. Sie wird normalerweise durch {@link Event#propagate()}
	 * ausgel�st.
	 */
	public void start() {
		started = true;
	}
	
	public int getListSize() {
		return eventListe.size();
	}
	
	public String getListElementAt(int eleCt) {
		return ((Event)eventListe.get(eleCt)).toString();
	}
}
