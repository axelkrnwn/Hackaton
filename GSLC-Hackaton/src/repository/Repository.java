package repository;

import java.util.ArrayList;

import connection.Connection;
import model.Model;

public interface Repository {
	
	public void insert(String[] data, Connection conn);
	
	public Model findOne(String column, String[] filter, Boolean join,
			String table, Connection conn); 
	
	public ArrayList<Model> find(String column, String[] filter, Boolean join,
			String table, Connection conn);
}
