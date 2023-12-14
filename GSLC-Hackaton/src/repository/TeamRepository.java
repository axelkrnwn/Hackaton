package repository;

import java.util.ArrayList;

import connection.Connection;
import model.Model;

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
		// TODO Auto-generated method stub
		return null;
	}

}
