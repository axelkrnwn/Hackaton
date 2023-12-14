package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Model;
import model.Team;
import model.User;

public class Connection {
	
	private static Connection conn;
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			conn = new Connection();
		}
		
		return conn;
	}
	
	public String openCSV(String name) {
		String path = "src/database/" + name.toLowerCase() + ".csv";
		return path;
	}
	
	public ArrayList<Model> readCSV(String csv){
		ArrayList<Model> models = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(openCSV(csv)));
			String line;
			while((line = br.readLine()) != null) {
				if (csv.equalsIgnoreCase("team")) {
					models.add(new Team(line));
				}else {
					String[] split = line.split(",");
					models.add(new User(split[0], split[2], split[1]));
				}
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return models;
	}
	
	public void writeCSV(String[] data, String name) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(openCSV(name),true));
			String toAdd = "";
			for (int i = 0;i < data.length;i++) {
				toAdd = toAdd + data[i];
				if (i < data.length - 1) {
					toAdd= toAdd + ",";
				}
			}
			bw.append(toAdd + "\n");
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
