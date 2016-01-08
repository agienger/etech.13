package teilX;

/**
 * Write a description of class Nand here.
 * 
 * @author Lion Gienger
 * @version (a version number or a date)
 */
public class Nand
{

    private Signal[] inputSignalArray;
    private Signal output;
    private int waitTime;
    protected int maxIteration = 10;
    private boolean lastCalculatedValue= false;

    /**
     * Constructor for objects of class Nand
     */
    public Nand(int anzahl)
    {

        inputSignalArray = new Signal[anzahl];
    }

    public Nand(int anzahl, int wTime)
    {
        this.waitTime = wTime;
        inputSignalArray = new Signal[anzahl];
    }

    public void setInput(int inputNummer, Signal inputSignal){
        inputSignalArray[inputNummer] = inputSignal;
        inputSignal.connectTo(this);
    }

    public void setOutput(Signal s){
        output = s;

    }

    public void calculateOutputValue (){
    
        boolean calculatedValue = nand();

        if ( (lastCalculatedValue != calculatedValue) && (maxIteration > 0) ) {
            maxIteration--;
            lastCalculatedValue = calculatedValue;
            output.setValue(lastCalculatedValue); // für findSteadyState() ohne
            // Eventerzeugung
        } else {
            System.out.println("Max erreicht für Ausgang: " + output.getName() +
                " value: " + lastCalculatedValue);
        }

        if ( output.getName().
equals("s0") || output.getName().
equals("s1") ){
            System.out.println (output.getName() + " = " + output.getValue());
        }
    }

    private boolean  nand(){
        for (int i = 0; i< inputSignalArray.length;i++){
            if (!inputSignalArray[i].getValue()){
                return true;
            }
        }
        return false;
    }
}

