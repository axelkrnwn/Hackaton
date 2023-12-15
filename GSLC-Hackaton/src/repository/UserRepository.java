package repository;

import java.util.ArrayList;

import connection.Connection;
import facade.queryFacade;
import model.Join;
import model.Model;
import model.Team;
import model.User;

public class UserRepository implements Repository {
	queryFacade facade = new queryFacade();

	@Override
	public void insert(String[] data, Connection conn) {
		// TODO Auto-generated method stub
		conn.writeCSV(data, "user");
	}

	@Override
	public Model findOne(String column, String[] filter, Boolean join, String table, Connection conn) {
		if (join == null || !join ) {
			ArrayList<Model> listModel = facade.readCSV(table);
			User u;
			if (filter != null && filter[0].equals("=")) {
				for (int i = 0; i < listModel.size(); i++) {
					u = (User) listModel.get(i);
					if (column.equalsIgnoreCase("name")) {
						if (u.getName().equals(filter[1])) {
							return u;
						}
					} else if (column.equalsIgnoreCase("teamName")) {
						if (u.getTeamName().equals(filter[1])) {
							return u;
						}
					}
					if (column.equalsIgnoreCase("NIM")) {
						if (u.getNim().equals(filter[1])) {
							return u;
						}
					}
				}
				return null;
			}
		}else {
			ArrayList<Model> listJoin = new ArrayList<Model>();
			ArrayList<Model> listUser;
			ArrayList<Model> listTeam;

			if (table.equalsIgnoreCase("user")) {
				listUser = facade.readCSV("user");
				listTeam = facade.readCSV("team");
				for (Model m : listUser) {
					User u = (User) m;
					listJoin.add(new Join(u, new Team(u.getTeamName())));
				}

				User u;
				Join j;
				
				if (filter != null && filter[0].equals("=")) {
					for (int i = 0; i < listJoin.size(); i++) {
						j = (Join) listJoin.get(i);
						u = j.getUser();
						if (column.equalsIgnoreCase("name")) {
							if (u.getName().equals(filter[1])) {
								return listJoin.get(i);
							}
						} else if (column.equalsIgnoreCase("teamName")) {
							if (u.getTeamName().equals(filter[1])) {
								return listJoin.get(i);
							}
						}
						if (column.equalsIgnoreCase("NIM")) {
							if (u.getNim().equals(filter[1])) {
								return listJoin.get(i);
							}
						}
					}
				}

				return null;

			} else {
				listUser = facade.readCSV("user");
				listTeam = facade.readCSV("team");

				for (Model m : listTeam) {
					Team t = (Team) m;
					for (Model m1 : listUser) {
						User u = (User) m1;
						if (u.getTeamName().equals(t.getName())) {
							return new Join(u,t);
						}
					}
				}
			}
			return null;

		}
		return null;
	}

	@Override
	public ArrayList<Model> find(String column, String[] filter, Boolean join, String table, Connection conn) {
		if (join == null || !join) {
			ArrayList<Model> listModel = facade.readCSV(table);
			ArrayList<Model> newList = new ArrayList<Model>();
			User u;
			if (filter != null && filter[0].equals("=")) {
				for (int i = 0; i < listModel.size(); i++) {
					u = (User) listModel.get(i);
					if (column.equalsIgnoreCase("name")) {
						if (u.getName().equals(filter[1])) {
							newList.add(listModel.get(i));
						}
					} else if (column.equalsIgnoreCase("teamName")) {
						if (u.getTeamName().equals(filter[1])) {
							newList.add(listModel.get(i));
						}
					}
					if (column.equalsIgnoreCase("NIM")) {
						if (u.getNim().equals(filter[1])) {
							newList.add(listModel.get(i));
						}
					}
				}
				return newList;
			}
			return listModel;

		} else {
			ArrayList<Model> listJoin = new ArrayList<Model>();
			ArrayList<Model> listUser;
			ArrayList<Model> listTeam;

			if (table.equalsIgnoreCase("user")) {
				listUser = facade.readCSV("user");
				listTeam = facade.readCSV("team");
				for (Model m : listUser) {
					User u = (User) m;
					listJoin.add(new Join(u, new Team(u.getTeamName())));
				}

				ArrayList<Model> newList = new ArrayList<Model>();
				User u;
				Join j;
				if (filter != null && filter[0].equals("=")) {
					for (int i = 0; i < listJoin.size(); i++) {
						j = (Join) listJoin.get(i);
						u = j.getUser();
						if (column.equalsIgnoreCase("name")) {
							if (u.getName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						} else if (column.equalsIgnoreCase("teamName")) {
							if (u.getTeamName().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}
						if (column.equalsIgnoreCase("NIM")) {
							if (u.getNim().equals(filter[1])) {
								newList.add(listJoin.get(i));
							}
						}
					}
					return newList;
				}

				return listJoin;

			} else {
				listUser = facade.readCSV("user");
				listTeam = facade.readCSV("team");

				for (Model m : listTeam) {
					Team t = (Team) m;
					for (Model m1 : listUser) {
						User u = (User) m1;
						if (u.getTeamName().equals(t.getName())) {
							listJoin.add(new Join(u, t));
						}
					}
				}
				return listJoin;
			}
		}

	}

	

}
