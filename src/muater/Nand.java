package muater;
public class Nand extends And
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Nand(int anzahleingaenge, int deltat)
    {
        super(anzahleingaenge,deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
        return (!super.WertBerechnen());
    }
}
