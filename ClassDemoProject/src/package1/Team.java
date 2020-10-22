package package1;

public class Team {

	private int teamId;
	private String teamName;
	private String coach;
	private String city;

	public Team(int teamId, String teamName, String city) {

		this.teamId = teamId;
		this.teamName = teamName;
		this.city = city;
	}
	
	public Team() {
		// TODO Auto-generated constructor stub
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
