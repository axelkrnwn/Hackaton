package facade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import connection.Connection;
import model.Model;
import model.Team;
import model.User;

public class queryFacade {

	Connection con;
	
	public ArrayList<Model> readCSV(String csv){
		ArrayList<Model> models = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(con.openCSV(csv)));
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
			BufferedWriter bw = new BufferedWriter(new FileWriter(con.openCSV(name),true));
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
	
	public queryFacade() {
		con = Connection.getInstance();
	}

}
