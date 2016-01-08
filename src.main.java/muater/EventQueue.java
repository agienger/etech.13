package muater;
import java.util.LinkedList;
import java.util.ListIterator;

import muater.Event;

public class EventQueue
{
    ////// Instanzvariablen
    public LinkedList eventschlange;
    private static int aktuellezeit;
    private boolean aktiv;
    private LinkedList gatterliste;
    
    ////// Konstruktoren
    public EventQueue()
    {
        eventschlange = new LinkedList();
        gatterliste = new LinkedList();
        aktuellezeit = 0;
        aktiv = false;
    }
    
    ////// Methoden  
    
    /**
     * Traegt die Events nach Zeit geordnet in die Eventschlange ein. Befindet sich kein Eventobjekt in der Liste, wird es sofort eingetragen.
     */ 
    public void EventHinzu(Event e)
    {
        int eventzeitpunkt = 0;
        eventzeitpunkt = e.GibEventZP();
        
        if (eventschlange.size() == 0) { eventschlange.addFirst(e); }
        else {
            int indexi = 0;
            int indexzeitpunkt = 0;
            boolean eingetragen = false;
           
            while( (indexi < eventschlange.size()) && (!eingetragen) ) {
                // Traegt das reinkommende Event zwischen den anderen Events in der Liste ein, au�er wenn Listenevent und reinkommendes Event den gleichen Zeipunkt haben
                // Sollten die Zeitpunkte gleich sein und die betreffenden Signale unterschiedlich, wird somit das Event bei dem n�chsten Schleifendurchlauf eingetragen
                indexzeitpunkt = ((Event)eventschlange.get(indexi)).GibEventZP();
                if(eventzeitpunkt < indexzeitpunkt) {
                    eventschlange.add(indexi,e);
                    eingetragen = true;
                } 
                // Ueberschreibt das Event in der Liste mit dem reinkommenden Event, sollte letzteres dasselbe Signal zum gleichen Zeitpunkt betreffen
                if( (eventzeitpunkt == indexzeitpunkt) && ((e.GibEventSignalname()).equals(((Event)eventschlange.get(indexi)).GibEventSignalname())) ) {
                    eventschlange.set(indexi,e);
                    eingetragen = true;
                }
                indexi++;
            }
            // Sollte das Event nicht durch die obige Schleife eingetragen worden sein, so muss es ganz an das Ende der Eventliste
            if(eingetragen == false) { eventschlange.addLast(e); }
        }
    }
    
    /**
     * Gibt zurueck, ob noch mehr EventObjekte in der Liste sind
     */ 
    public boolean hasMore()
    {
        boolean habenochevents = false;
        if(eventschlange.size() > 0) { habenochevents = true; }
        return habenochevents;
    }
    
    /**
     * Gibt das erste EventObjekt in der Queue zurueck
     */    
    public Event getFirst()
    {
        return (Event)eventschlange.getFirst();
    }
    
    public String toString() {
		String output = "";
		ListIterator<Event> iterator = eventschlange.listIterator();
		while (iterator.hasNext()) {
			Event e = iterator.next();
			output += e.GibEventZP() + ":" + e.GibEventSignalname() + ":"
					+ e.GibEventSignal().getValue() + ", ";
		}
		return output;
	}
    
    /**
     * Sobald das Event seine Aenderungen propagiert hat, greift es auf diese Methode der Queue zu um sich selber von der ersten Position
     * aus der Eventliste zu loeschen
     */   
    public void EventLoeschen()
    {
        if( (eventschlange.size() > 1) && (((Event)eventschlange.get(0)).GibEventZP() < ((Event)eventschlange.get(1)).GibEventZP()) ) {
            int indexi = 0;
            while(indexi < gatterliste.size()) {
                ((Gatter)gatterliste.get(indexi)).SignalWeitergeben();
                indexi++;
            }
        }       
        
        eventschlange.removeFirst();
        
        if(eventschlange.size() == 0) {
            int indexi = 0;
            while(indexi < gatterliste.size()) {
                ((Gatter)gatterliste.get(indexi)).SignalWeitergeben();
                indexi++;
            }
        }
    }
    
    public void GatterHinzu(Gatter g)
    {
        gatterliste.add(g);
    }
    
    /**
     * Events lassen mit dieser Methode die Zeit voranschreiten, wenn sie im propagieren begriffen sind
     */
    public void SetzeZeitpunkt(int t)
    {
        if(aktuellezeit < t) { aktuellezeit = t; }
    }
    
    /**
     * Diese Methode gibt den aktuellen Zeippunkt zurueck
     */
    public static int GibZeitpunkt()
    {
        return aktuellezeit;
    }
    
    /**
     * Diese Methode setzt die Queue zur�ck d.h. der QueueZeitzaehler wird auf 0 gesetzt und sie wird deaktiviert - wird aber so eigentlich in Teilaufgabe2 nicht gebraucht
     */ 
    //public void QueueReset()
    //{
    //        EventQueueDeaktivieren();
    //        System.out.println("### Vergangene Simulationszeit: " + GibZeitpunkt() );
    //        SetzeZeitpunkt(0);
    //}
    
    ////// Die folgenden drei Methoden dienen zu (De-)Aktivierung der EventQueue bzw. zur Abfrage dessen. Momentan werden diese noch nicht gebraucht aber sp�ter dann
    public void EventQueueAktivieren()
    {
        aktiv=true;
    }
    
    public void EventQueueDeaktivieren()
    {
        aktiv=false;
    }
    
    public boolean EventQueueAktiv()
    {
        return aktiv;
    }    
}