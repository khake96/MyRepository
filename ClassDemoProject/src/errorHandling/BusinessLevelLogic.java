package errorHandling;

public class BusinessLevelLogic {
	
	

	public static void main(String[] args) {
		
		PresentationLevel pl = new PresentationLevel();

			// check for valid mobile number
		try {
			pl.isMobileValid("+111123456784");
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
			// check for valid age
		try {
			pl.isAgeValid(-1);
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
	}
}
