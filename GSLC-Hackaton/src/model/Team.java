package model;

import java.util.ArrayList;

public class Team extends Model {
	
	private ArrayList<User> members;
	
	public Team(String name) {
		super(name);
		members = new ArrayList<>();
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	
	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}


}
