package repository;

import java.util.ArrayList;

import connection.Connection;
import model.Join;
import model.Model;
import model.Team;
import model.User;

public class TeamRepository implements Repository{

	@Override
	public void insert(String[] data, Connection conn) {
		// TODO Auto-generated method stub
		conn.writeCSV(data, "team");
	}

	@Override
	public Model findOne(String column, String[] filter, Boolean join, String table, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Model> find(String column, String[] filter, Boolean join, String table, Connection conn) {
		if(!join) {
			ArrayList<Model> listModel = conn.readCSV(table);
			ArrayList<Model> newList = new ArrayList<Model>();
			Team u;
			if(filter!= null && filter[0].equals("=")) {
				for (int i=0;i<listModel.size();i++) {
					u = (Team)listModel.get(i);
					if(column.equalsIgnoreCase("teamName")) {
						if(u.getName().equals(filter[1])) {
							newList.add(listModel.get(i));
						}
					}
				}
				return newList;
			}
			return listModel;
			
		}else {
			ArrayList<Model> listJoin = new ArrayList<Model>();
			ArrayList<Model> listUser;
			ArrayList<Model> listTeam;
			
			if(table.equalsIgnoreCase("user")) {
				listUser = conn.readCSV("user");
				listTeam = conn.readCSV("team");
				for (Model m : listUser) {
					User u = (User)m;
					listJoin.add(new Join(u, new Team(u.getTeamName())));
//					System.out.println(u.getName());
				}
				
				ArrayList<Model> newList = new ArrayList<Model>();
				User u;
				Join j;
				if(filter!= null &&filter[0].equals("=")) {
					for (int i=0;i<listJoin.size();i++) {
						j = (Join) listJoin.get(i);
						u = j.getUser();
//						System.out.println(column);
						if(column.equalsIgnoreCase("name")) {
//							System.out.println(u.getName());
							if(u.getName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}else if(column.equalsIgnoreCase("teamName")) {
							if(u.getTeamName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}if(column.equalsIgnoreCase("NIM")) {
							if(u.getNim().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}
					}
					return newList;
				}
				
				return listJoin;
				
				
				
				
			}else {
				listUser = conn.readCSV("user");
				listTeam = conn.readCSV("team");
				
				for (Model m : listTeam) {
					Team  t = (Team)m;
					for (Model m1 : listUser) {
						User u = (User)m1;
						if(u.getTeamName().equals(t.getName())) {
							listJoin.add(new Join(u, t));
						}
					}
				}
				ArrayList<Model> newList = new ArrayList<Model>();
				User u;
				Join j;
				if(filter!= null &&filter[0].equals("=")) {
					for (int i=0;i<listJoin.size();i++) {
						j = (Join) listJoin.get(i);
						u = j.getUser();
//						System.out.println(column);
						if(column.equalsIgnoreCase("name")) {
//							System.out.println(u.getName());
							if(u.getName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}else if(column.equalsIgnoreCase("teamName")) {
							if(u.getTeamName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}if(column.equalsIgnoreCase("NIM")) {
							if(u.getNim().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}
					}
					return newList;
				}
				return listJoin;
			}
		}
		
	}

}
