package teilX;

/**
 * Write a description of class Signal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Signal
{
    // instance variables - replace the example below with your own
    private String signalName;
    private boolean signalValue;
    private ArrayList<Nand> gatterList;

    /**
     * Constructor for objects of class Signal
     */
    public Signal(String name)
    {
        setName(name);
        gatterList = new ArrayList<Nand>();
    }

    public void connectTo (Nand gatter){
        gatterList.add(gatter);
    }

    public void setValue(boolean value)
    {
        signalValue = value;
        for (Nand gatter: gatterList){
            gatter.calculateOutputValue();  
        }

       
    }

    public String getName(){
        return signalName;
    }

    public void setName(String name){
        this.signalName = name;
    }

    public boolean getValue(){
        return signalValue;
    }
}
