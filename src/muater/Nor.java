package muater;
public class Nor extends Or
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Nor(int anzahleingaenge, int deltat)
    {
        super(anzahleingaenge,deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        return (!super.WertBerechnen());
    }
}
