package MathBaseball.math_bb2;

public class Teacher {
	String firstName;
	String lastName;
	
	public Teacher(){
		
	}
	
	public Teacher(String first, String last){
		firstName = first;
		lastName = last;
	}
	
	public void setFirstName(String name){
		firstName = name;
	}
	
	public void setLastName(String name){
		lastName = name;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}

}
