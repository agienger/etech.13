package muater;
public class Latch extends Gatter
{
    ////// Zusaetzliche Instanzvariablen - Keine
    
    ////// Konstruktor
    public Latch(int deltat)
    {
        super(deltat);
    }
    
    ////// Methoden
    public boolean WertBerechnen()
    {      
        boolean enable,wert,ausgabe = false;
       
        enable = ((Signal)eingangssignalliste.get(0)).getValue();
        wert = ((Signal)eingangssignalliste.get(1)).getValue();
        
        if (enable == true) {ausgabe = wert;}
        else {ausgabe = vorherigerwert;}
        return ausgabe;    
    }
}
