package muater;
public class Not extends Buf
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Not(int deltat)
    {
        super(deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {
            return (!super.WertBerechnen());
    }
}