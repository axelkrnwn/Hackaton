package helper;

import java.util.ArrayList;

public class Helper {

	public static boolean isTeamFull(String teamName, int max) {
		ArrayList<String[]> teamData = new ArrayList<String[]>();
		int count = 0;

		for (String[] row : teamData) {
			if (row[0].equals(teamName)) {
				count++;
			}
		}

		return count >= max;
	}

}
