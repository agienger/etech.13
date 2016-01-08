package muater;
public class Buf extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Buf(int deltat)
    {
        super(deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        return (((Signal)eingangssignalliste.get(0)).getValue());
    }
}

