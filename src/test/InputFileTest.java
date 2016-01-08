package test;

import static org.junit.Assert.*;
import gatter.Gatter;

import java.util.Arrays;
import java.util.List;

import main.CircuitFile;

import org.junit.Test;

public class InputFileTest {

	@Test
	public void test() {
		String fileName = "C:\\Users\\d028623\\dev\\eclpiseWorkspace\\etp\\src\\test\\test.cir";
		CircuitFile inputFile = new CircuitFile(fileName);
		List<String> expectedInputs = Arrays.asList("a", "b", "cin");
		List<String> expectedOutputs = Arrays.asList("s", "cout");
		List<String> expectedSignals = Arrays.asList("i1", "i2", "i3");
		for (int i = 0; i < expectedInputs.size(); i++) {
			assertTrue(inputFile.getInputs().containsKey(expectedInputs.get(i)));
		}
		for (int i = 0; i < expectedOutputs.size(); i++) {
			assertTrue(inputFile.getOutputs().containsKey(expectedOutputs.get(i)));
		}
		for (int i = 0; i < expectedSignals.size(); i++) {
			assertTrue(inputFile.getSignals().containsKey(expectedSignals.get(i)));
		}
		assertTrue(inputFile.getGates().containsKey("g1"));
		Gatter gate = inputFile.getGates().get("g1");
		assertEquals(4,  gate.getWaitTime());
		assertEquals(2,  gate.getInputSignalArray().length);
//		AND2  Delay 4;, actual);
	}

}
