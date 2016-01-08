package teilX;
/**
 * Die Klasse And erbt von der Klasse Gatter <br>
 * Sie stellt ein AND-Gatter mit beliebig vielen Eing�ngen dar. <br>
 * <P>
 * Eine Tabelle f�r ein einfaches AND mit zwei Eing�ngen <b>x</b> und <b>y</b> 
 * <P>
 * <table border="2" cellpadding="4" cellspacing="0" rules="all">
 * <tr>
 * <th style="background-color: rgb(240, 240, 240);" width="50"><b>x</b></th>
 * <th style="background-color: rgb(240, 240, 240);" width="50"><b>y</b></th>
 * <th style="background-color: rgb(240, 240, 240);" width="80"><b>x AND y</b></th>
 * </tr>
 * <tr align="center">
 * <td>0</td>
 * <td>0</td>
 * <td><b>0</b></td>
 * </tr>
 * <tr align="center">
 * <td>0</td>
 * <td>1</td>
 * <td><b>0</b></td>
 * </tr>
 * <tr align="center">
 * <td>1</td>
 * <td>0</td>
 * <td><b>0</b></td>
 * </tr>
 * <tr align="center">
 * <td>1</td>
 * <td>1</td>
 * <td><b>1</b></td>
 * </tr>
 * </table>
 * 
 * @author Nils-Team
 * @version 1.0 25.05.06
 * @see Gatter
 */
public class And extends Gatter {

	/**
	 * Konstruktor f�r Objekte der Klasse And
	 * 
     * @param anzEin	die Anzahl der anzulegenden Eing�nge
	 * 
	 * @see Gatter#Gatter(int)
	 */
	public And(int anzEin) {
		super(anzEin);
		art = "AND";
	}

	/**
	 * Konstruktor f�r Objekte der Klasse And
	 * 
     * @param 	anzEin		die Anzahl der anzulegenden Eing�nge
     * @param	waitTime 	die DelayTime dieses Ands
	 * 
	 * @see Gatter#Gatter(int, int)
	 */
	public And(int anzEin, int waitTime) {
		super(anzEin, waitTime);
		art = "AND";
	}

	/** 
	 * Die Methode calcOutput() berechnet den Wert des Ausgangs f�r das Gatter.
	 * 
	 * @return Wert des berechneten Ausgangs. (siehe {@link And}) 
	 * @see Gatter#calcOutput()
	 */
	public boolean calcOutput() {
    	boolean ergebnis = true;
    	int i = 0;
    	while ((ergebnis == true) && (i < eingang.length)) {
    		ergebnis &= eingang[i].getValue();
    		i++; 
    	}
    	return ergebnis;
	}

}
