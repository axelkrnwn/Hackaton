package model;

public class Join extends Model {
	
	private User user;
	private Team team;
	public Join(User user, Team team) {
		super("");
		this.user = user;
		this.team = team;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	

}
