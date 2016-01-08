package muater;
public class Or extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Or(int anzahleingaenge, int deltat)
    {
        super(anzahleingaenge,deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        int indexi = 0;
        boolean ausgabesignalwert = false;
        
        while (indexi < anzahleingangssignale) {
            ausgabesignalwert |= (((Signal)eingangssignalliste.get(indexi)).getValue());
            indexi++;
        }
        return ausgabesignalwert;
    }
}

