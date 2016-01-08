package teil2.test;

import java.util.ArrayList;

public class Solutions {

	public static ArrayList<String[]> getExpectedResultExcercise2() {
		ArrayList<String[]> resultList = new ArrayList<String[]>();
		resultList.add(new String[] { "S", "10", "true" });
		resultList.add(new String[] { "S", "30", "false" });
		resultList.add(new String[] { "nQ", "30", "false" });
		resultList.add(new String[] { "Q", "40", "true" });
		resultList.add(new String[] { "R", "50", "true" });
		resultList.add(new String[] { "Q", "70", "false" });
		resultList.add(new String[] { "nQ", "80", "true" });
		resultList.add(new String[] { "R", "100", "false" });
		resultList.add(new String[] { "S", "120", "true" });
		resultList.add(new String[] { "nQ", "140", "false" });
		resultList.add(new String[] { "R", "150", "true" });
		resultList.add(new String[] { "Q", "150", "true" });
		resultList.add(new String[] { "S", "170", "false" });
		resultList.add(new String[] { "Q", "170", "false" });
		resultList.add(new String[] { "R", "180", "false" });
		resultList.add(new String[] { "nQ", "190", "true" });
		return resultList;
	}

}
