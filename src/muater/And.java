package muater;

public class And extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public And(int anzahleingaenge, int deltat)
    {
        super(anzahleingaenge,deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        int indexi = 0;
        boolean ausgabesignalwert = true;
        
        while (indexi < anzahleingangssignale) {
            ausgabesignalwert &= (((Signal)eingangssignalliste.get(indexi)).getValue());
            indexi++;
        }
        return ausgabesignalwert;
    }
}
