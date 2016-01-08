package muater;
/**
 * Signal
 * 
 * @author (Alexander Trautmann & Torsten Haase) 
 * @version (v1.0)
 */

import java.lang.String;
import java.util.ArrayList;

public class Signal
{
    ////// Instanzvariablen
    private boolean signalwert;
    private String signalname;
    private ArrayList gatterliste;
    private int zaehler;
    
    ////// Konstruktor
    public Signal(String zugewiesenersignalname) 
    {
        signalwert = false;
        signalname = zugewiesenersignalname;
        gatterliste = new ArrayList();
        zaehler = 0;
    }

    //////Methoden
    public boolean getValue() 
    {
        return signalwert;
    }
    
    public String GibSignalname()
    {
        return signalname;
    }
    
    /**
     * Ueber diese Methode koennen die Gatter den Signalwert ver?ndern und das Signal selber gibt diese
     * Aenderung an alle Gatter in der ArrayList weiter, so dass die Gatter neu berechnen.
     */
    public void setValue(boolean neuersignalwert)
    {
        signalwert = neuersignalwert;
        
        if(((Event.returnEventQueue()).EventQueueAktiv() == false) && (zaehler < 10) ){           
            zaehler++;
            int indexi = 0;
            while (indexi < gatterliste.size()) {
                ((Gatter)gatterliste.get(indexi)).SignalWeitergeben();
                indexi++;
            }
        }
        


          // Ausgaben fuer 1
          if( ((GibSignalname()).equals("Out")) && ((Event.returnEventQueue()).EventQueueAktiv() == true) ) {
              System.out.println(EventQueue.GibZeitpunkt() + ": " + (GibSignalname()) + "=" + (signalwert));
          }
          // Ausgaben fuer 2
          if( ((GibSignalname()).equals("Out0") || (GibSignalname()).equals("Out1") || (GibSignalname()).equals("Out2") || (GibSignalname()).equals("Out3")) && ((Event.returnEventQueue()).EventQueueAktiv() == true) ) {
              System.out.println(EventQueue.GibZeitpunkt() + ": " + (GibSignalname()) + "=" + (signalwert));
          }
           //AUsgaben fuer 3
          if( ((GibSignalname()).equals("memOut0") || (GibSignalname()).equals("memOut1") || (GibSignalname()).equals("memOut2") || (GibSignalname()).equals("CntAddr0") || (GibSignalname()).equals("CntAddr1") || (GibSignalname()).equals("CntAddr2")) && ((Event.returnEventQueue()).EventQueueAktiv() == true) ) {
              System.out.println(EventQueue.GibZeitpunkt() + ": " + (GibSignalname()) + "=" + (signalwert));
          }
    }
    
    public void IchBinDortAngeschlossen(Gatter angeschlossen)
    {
         gatterliste.add(angeschlossen);
         //System.out.println(GibSignalname() + " angeschlossen");
    }   
}