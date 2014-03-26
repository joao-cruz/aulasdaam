package pt.iscte.daam.soccerleague;

public class Teams {
	private long id;
	private String teamId="";
	private String teamName="";
	private String teamcity ="";
	private String teamFY = "";

	public Teams(String teamId, String teamName, String teamCity, String teamFY) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamcity = teamCity;
		this.teamFY = teamFY;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTeamId() {
		return teamId;
	}
	
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamcity() {
		return teamcity;
	}

	public void setTeamcity(String teamcity) {
		this.teamcity = teamcity;
	}

	public String getTeamFY() {
		return teamFY;
	}

	public void setTeamFY(String teamFY) {
		this.teamFY = teamFY;
	}
}