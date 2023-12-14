package model;

public class User extends Model {
	
	private String teamName;
	private String nim;

	public User(String name, String teamName, String nim) {
		super(name);
		this.teamName = teamName;
		this.nim = nim;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	

}
