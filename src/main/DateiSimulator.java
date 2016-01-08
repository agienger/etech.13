package main;

import java.io.FileNotFoundException;
import java.util.HashMap;

import event.Event;
import event.EventQueue;
import event.Signal;
import file.DateiLeser;
import gatter.Gatter;

public class DateiSimulator {

	// EventQueue fuer diesen Simulator, wird im Konstruktor initialisiert
	private EventQueue queue;

	private HashMap<String, Signal> signals;
	private HashMap<String, Gatter> gates;

	/**
	 * Konstruktor, der die zu simulierende Schaltung aufbaut, den Ruhezustand
	 * ermittelt und die Eingabe-Events erzeugt. Simuliert wird je nach Argument
	 * eine der drei vorgegebenen Schaltungen 1 = Einfacher Multiplexer 4 zu 1 2
	 * = Einfacher synchroner, ruecksetzbarer Zaehler mit 4 Bit 3 = Komplexe
	 * Schaltung mit einem Zaehler vielen Latches und einigen Multiplexern
	 */
	public DateiSimulator(String circuitFileName, String eventFileName) {

		queue = new EventQueue();
		Event.setEventQueue(queue);

		// Schaltung aufbauen
		buildCircuit(circuitFileName);
		// Ruhezustand berechnen
//		findSteadyState();
		// EventQueue mit Eingabe-Events fuellen
		setInputEvents(eventFileName);

	}

	private void setInputEvents(String eventFileName) {
		DateiLeser fileReader = new DateiLeser(eventFileName);
		
		while (fileReader.nochMehrZeilen()) {
			String line = fileReader.gibZeile();
			if (line.startsWith("#") || line.replaceAll("\\s","").equals("")) {
				continue;
			}
			String [] eventArray = new String[3];
			eventArray = line.split("\\s+");
			Signal s = signals.get(eventArray[1]);
			int startTime = Integer.parseInt(eventArray[0]);
			boolean value = eventArray[2].equals("1") ? true: false;
			new Event(s,startTime,value);
		}
	}

	private void buildCircuit(String fileName) {
		CircuitFile inputFile = new CircuitFile(fileName);

		signals = inputFile.getSignals();
//		gates = inputFile.getGates();
	}

//	private void findSteadyState() {
//		for (Signal inputSignal: inputs.values()) {
//			inputSignal.setValue(false);
//		}
//	}

	/**
	 * Diese Methode fuehrt die eigentliche Simulation durch. Dazu wird
	 * geprueft, ob in der EventQueue noch weitere Events vorhanden sind. Ist
	 * dies der Fall, dann wird das nächste anstehende Event behandelt. Dazu
	 * muss das Event die Methode propagate() zur Verfügung stellen, die dann
	 * das betroffene Signal informiert.
	 */
	public void simulate() {
		while (queue.hasMore()) {
			Event e = queue.getFirst();
			e.propagate();
		}
	}

	static public void main(String[] args) throws FileNotFoundException {
		String circuitFileName = "C:\\Users\\d028623\\dev\\eclpiseWorkspace\\etp\\src\\exampleFiles\\beispiel1o.cir";
		String eventFileName = "C:\\Users\\d028623\\dev\\eclpiseWorkspace\\etp\\src\\exampleFiles\\beispiel1o.events";
		DateiSimulator t = new DateiSimulator(circuitFileName, eventFileName);
		t.simulate();
	}
}