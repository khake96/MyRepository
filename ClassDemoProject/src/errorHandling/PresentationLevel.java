package errorHandling;

public class PresentationLevel {
	
	public boolean isMobileValid(String mobile) throws BusinessException{
		
		if(!mobile.matches("\\+1[0-9]{10}")) {
			
			throw new BusinessException("Mobile number: " + mobile + " is invalid.");
		
		} else {
			System.out.println("Mobile number: "+ mobile +" is valid.");	
			return true;
		} 
		
	}
	
	public boolean isAgeValid(int age) throws BusinessException {
		
		if(age<=0) {
			throw new BusinessException("Age cannot be less than or equal to zero.");
		} else if(60<age) {
			throw new BusinessException("Age cannot be over 60.");
		} else {
			System.out.println("Age: "+age+" is valid.");
			return true;
		}
		
	}
}
