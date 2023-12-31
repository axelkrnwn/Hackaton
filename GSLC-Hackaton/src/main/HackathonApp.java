package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import connection.Connection;
import helper.Helper;
import model.Join;
import model.Model;
import model.User;
import repository.TeamRepository;
import repository.UserRepository;

public class HackathonApp {

	private static final int MAX_USERS_PER_TEAM = 3;
	Scanner scanner = new Scanner(System.in);
	
	void displayMenu() {
		System.out.println("1. Menu Utama");
		System.out.println("2. Insert Data");
		System.out.println("3. Show");
		System.out.println("4. Exit");
	}

	void displayInsertMenu() {
		System.out.println("Which table to insert? 1. User, 2. Team.");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
			insertUserData();
			break;
		case 2:
			insertTeamData();
			break;
		default:
			System.out.println("Pilihan tidak valid. Silakan coba lagi.");
		}
	}

	void insertUserData() {

		System.out.println("Add name:");
		String name = scanner.nextLine();

		System.out.println("Add nim:");
		String nim = scanner.nextLine();

		System.out.println("Add team:");
		String team = scanner.nextLine();

		if (Helper.isTeamFull(team,MAX_USERS_PER_TEAM)) {
			System.out.println("Error: Team full.");
		} else {
			addUserToTeam(name, nim, team);
			System.out.println("User created!");
		}
	}
	

	void addUserToTeam(String name, String nim, String team) {
		String[] newUser = { name, nim, team };
		Connection con = Connection.getInstance();
		UserRepository u = new UserRepository();
		u.insert(newUser, con);
	}

	void insertTeamData() {

		System.out.println("Add team name:");
		String teamName = scanner.nextLine();

		addTeam(teamName);
		System.out.println("Team created!");
	}

	void addTeam(String teamName) {
		String[] newTeam = { teamName };
		Connection con = Connection.getInstance();
		
		TeamRepository t = new TeamRepository();
		t.insert(newTeam, con);
	}

	void displayShowMenu() {
		System.out.println("Which table to show? 1. User, 2. Team.");
		int choice = scanner.nextInt();

		System.out.println("Want to filter by condition? 1. Yes, 2. No.");
		int filterChoice = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		if (filterChoice == 1) {
			System.out.println("Add condition, separate by semicolon");
			String condition = scanner.nextLine();
			System.out.println("Join table or no? [Y|N]");
			String joinTable = scanner.nextLine();
			boolean join = Helper.checkYesNo(joinTable);
			showDataWithCondition(choice, condition,join);
		} else {
			showData(choice);
		}
	}

	void showData(int choice) {
		Connection conn = Connection.getInstance();
		if (choice == 1) {
			UserRepository r = new UserRepository();
			ArrayList<Model> users = r.find("", null, false, "user", conn);
			System.out.println("test");
			for (Model m : users) {
				System.out.println("Username : " + m.getName() + "\n"
						+ "NIM      : " + ((User)m).getNim() + "\nTeam     : " + ((User)m).getTeamName() + "\n");
			}
			
		}else {
			TeamRepository t = new TeamRepository();
			ArrayList<Model> teams = t.find("", null, false, "team", conn);
			for (Model p : teams) {
				System.out.println("Team Name : " + p.getName());
			}
		}
	}

	void showDataWithCondition(int choice, String condition, boolean join) {
		String[] splits = condition.split(";");
		String[] conditions = condition.substring(condition.indexOf(';') + 1).
				split(";");
		String column = splits[0];
		
		
//		System.out.println(conditions[0] + " " + conditions[1]);
		Connection conn = Connection.getInstance();
		if (choice == 1) {
			UserRepository r = new UserRepository();
			ArrayList<Model> users = r.find(column, conditions, join, "user", conn);
			if(!join) {
				for (Model m : users) {
					System.out.println("Username : " + m.getName() + "\n"
							+ "NIM      : " + ((User)m).getNim() + "\nTeam     : " + ((User)m).getTeamName() + "\n");
				}
			}else {
				Join j;
				for (Model m : users) {
					j = (Join)m;
					System.out.printf("Username : %s\n", j.getUser().getName());
					System.out.printf("NIM : %s\n", j.getUser().getNim());
					System.out.printf("TeamName : %s\n", j.getTeam().getName());
				}
			}
			
		}else {
			TeamRepository t = new TeamRepository();
			ArrayList<Model> teams = t.find(column,conditions, join, "team", conn);
			if(!join) {
				for (Model p : teams) {
					System.out.println("Team Name : " + p.getName());
				}
				
			}else {
				Join j;
				for (Model m : teams) {
					j = (Join)m;
					System.out.printf("Username : %s\n", j.getUser().getName());
					System.out.printf("NIM : %s\n", j.getUser().getNim());
					System.out.printf("TeamName : %s\n\n", j.getTeam().getName());
				}
			}
		}
	}
	
	void displayMainMenu() {
		displayMenu();
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch (choice) {
		case 1:
//			displayMainMenu();
			break;
		case 2:
			displayInsertMenu();
			break;
		case 3:
			displayShowMenu();
			break;
		case 4:
			System.exit(0);
		default:
			System.out.println("Pilihan tidak valid. Silakan coba lagi.");
		}
	}
	
	public HackathonApp() {
		while (true) {
			displayMainMenu();
		}
		
	}
	
	public static void main(String[] args) {
		new HackathonApp();
	}

	
	
}