package package1;

public class Player {
	
	private String name;
	private int id;
	private Address address;
	private Team team;

	
	public Player(String name, int id, Address address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	

}
