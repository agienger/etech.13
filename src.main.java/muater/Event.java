package muater;
public class Event
{
    ////// Instanzvariablen
    private static EventQueue eventschlange;
    private int eventzeitpunkt;
    private boolean neuersignalwert;
    private Signal betreffendessignal;
    
    ////// Konstruktor
    public Event(Signal s,int ez,boolean nsw)
    {
        betreffendessignal = s;
        eventzeitpunkt = ez;
        neuersignalwert = nsw;
        eventschlange.EventHinzu(this);
    }

    ////// Methoden
    public static void setEventQueue(EventQueue e)
    {
        eventschlange = e;
    }
    
    public static EventQueue returnEventQueue()
    {
        return eventschlange;
    }
    
    public int GibEventZP()
    {
        return eventzeitpunkt;
    }
    
    public Signal GibEventSignal()
    {
        return betreffendessignal;
    }
    
    public String GibEventSignalname()
    {
        return betreffendessignal.GibSignalname();
    }
     
    public void propagate()
    {
    	
        if(eventschlange.EventQueueAktiv() == false) {eventschlange.EventQueueAktivieren();}
//        System.out.println(eventschlange.toString());
        eventschlange.SetzeZeitpunkt(eventzeitpunkt);
        betreffendessignal.setValue(neuersignalwert);
        eventschlange.EventLoeschen();
    }
}
