package muater;
public class Exor extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Exor(int anzahleingaenge, int deltat)
    {
        super(anzahleingaenge,deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        int indexi = 0;
        int anzahltrues = 0;
        
        while (indexi < anzahleingangssignale) {
            if( (((Signal)eingangssignalliste.get(indexi)).getValue()) ) {
                anzahltrues++;
            }
            indexi++;
        }
        if( (anzahltrues%2) == 1) { return true; }
        else { return false; }
    }
}