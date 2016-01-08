package muater;
public class FF extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    boolean letztertakt;
    
    ////// Konstruktor
    public FF(int deltat)
    {
        super(deltat);
        letztertakt = false;
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {           
        boolean takt,wert,ausgabe = false;
        
        takt = ((Signal)eingangssignalliste.get(0)).getValue();
        wert = ((Signal)eingangssignalliste.get(1)).getValue();
        
        if ((letztertakt == false) && (takt == true)) {
            letztertakt = takt;
            ausgabe = wert;
        } else {
            letztertakt = takt;
            ausgabe = vorherigerwert;
        }
        return ausgabe;   
    }
}