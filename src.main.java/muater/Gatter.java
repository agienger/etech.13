package muater;
/**
 * Gatter Superklasse
 * 
 * @author (Alexander Trautmann & Torsten Haase) 
 * @version (v1.0)
 * 
 */

import java.util.ArrayList;

public abstract class Gatter
{
    ////// Instanzvariablen
    protected int anzahleingangssignale;
    protected int zeitverzoegerung;
    protected ArrayList eingangssignalliste;
    protected Signal ausgangssignal;
    protected boolean vorherigerwert;
    
    //////Konstruktor fuer Objekte der Klasse Gatter
    public Gatter(int anzahleingaenge, int deltat)
    {
        anzahleingangssignale = anzahleingaenge;
        zeitverzoegerung = deltat;
        eingangssignalliste = new ArrayList();
        (Event.returnEventQueue()).GatterHinzu(this);
        vorherigerwert = false;

    }
    
    public Gatter(int deltat)
    {
        zeitverzoegerung = deltat;
        eingangssignalliste = new ArrayList();
        (Event.returnEventQueue()).GatterHinzu(this);
    }

    //////Methoden des Gatters   
    /**
     * Speichert Ausgangssignalobjekt zum abrufen von dessen Methoden
     */
    public void setOutput(Signal a)
    {
        ausgangssignal = a;
    }
    /**
     * Speichert die vom Simulator ?bergebenen Eingangsignalobjekte in einer Arraylist damit auf deren Methoden zugegriffen werden kann
     * und das Gatter ?bergibt sich selber an diese Signal-Objekt, damit diese wissen von welchem Gatterobjekt sie die Methoden ausf?hren m?ssen.
     */   
    public void setInput(int signalnummer,Signal eingangssignalobjekt)
    {
        eingangssignalliste.add(signalnummer,eingangssignalobjekt);
        eingangssignalobjekt.IchBinDortAngeschlossen(this);
    }
    
    /**
     * Dies ist die eigentliche Gatter-Berechnung 
     */  
    public abstract boolean WertBerechnen();
    
    /**
     * Diese Methode stellt die Hauptkommunikation zwischen Signal und Gatter dar. Ja nachdem ob zeitlose Signalweitergabe oder nicht ?ndert sie direkt sofort das
     * Ausgangssignal oder erzeugt ein Folgeevent.
     */
    public void SignalWeitergeben()
    {
        boolean arbeitswert = this.WertBerechnen();
        
        // Zeitlose Signalweitergabe wird duer SteadyState benoetigt
        if((Event.returnEventQueue()).EventQueueAktiv() == false) {
            ausgangssignal.setValue(arbeitswert);
            vorherigerwert = arbeitswert;
        }
        
        // Setzt ein wenn die EventQueue und damit die Eventsteuerung aktiv wird
        if( (vorherigerwert != arbeitswert) && ((Event.returnEventQueue()).EventQueueAktiv() == true )) {
            vorherigerwert = arbeitswert;
            int zeitmomentan = EventQueue.GibZeitpunkt();
            new Event(ausgangssignal,(zeitmomentan+zeitverzoegerung),arbeitswert);
        }
    }          
}
