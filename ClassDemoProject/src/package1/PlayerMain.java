package package1;

public class PlayerMain {

	public static void main(String[] args) {

		Address a1 = new Address(150,"Manuel St.","Suite 145","Chicago", "IL", 54078);
		Player p1 = new Player("Thomas Egret",120,a1);
		Team t1 = new Team(563,"Blue Devils","Chicago");
		p1.setTeam(t1);
		
		System.out.println("Printing p1");
		System.out.println("Player Name: "+p1.getName());
		System.out.println("Player Team: "+t1.getTeamName());
		System.out.println("Player Address: ");
		Address.printAddress(a1);
		
	}

}
