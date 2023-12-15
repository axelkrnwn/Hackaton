package helper;

import java.util.ArrayList;

import connection.Connection;
import model.Model;
import model.Team;
import model.User;
import repository.TeamRepository;
import repository.UserRepository;

public class Helper {

	public static boolean isTeamFull(String teamName, int max) {
		Connection conn = Connection.getInstance();
		TeamRepository t = new TeamRepository();
		String[] filter = {"=",teamName};
		Team teamData = (Team) t.findOne("teamName",filter, null, "team", conn);
		
		UserRepository u = new UserRepository();
				
		ArrayList<Model> users = u.find(null, null, null, "user", conn);
		int count = 0;
		
		for (Model user : users) {
			if (((User) user).getTeamName().equals(teamData.getName())) {
				count++;
			}
		}

		return count >= max;
	}
	
	public static boolean checkYesNo(String input) {
		if(input.equalsIgnoreCase("Y")) {
			return true;
		}else if(input.equalsIgnoreCase("N")) {
			return false;
		}
		return false;
	}

}
