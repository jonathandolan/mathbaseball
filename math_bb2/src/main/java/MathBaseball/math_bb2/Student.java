package MathBaseball.math_bb2;

public class Student {
	int score;
	int questionsAttempted;
	int questionsRight;
	String userName;
	String firstName;
	String lastName;
	
	public Student(String first, String last){
		firstName = first;
		lastName = last;
	}
	
	public Student() {
		
	}

	public void setScore(int newScore){
		score = newScore;
	}
	
	public void setAttempts(int attempts){
		questionsAttempted = attempts;
	}
	
	public void setCorrect(int correct){
		questionsRight = correct;
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public void setFirstName(String name){
		firstName = name;
	}
	
	public void setLastName(String name){
		lastName = name;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getAttempts(){
		return questionsAttempted;
	}
	
	public int getCorrect(){
		return questionsRight;
	}
	
	public String getUserName(){
		return userName;
	}

	public String getFirstName(){
		return firstName;
	}
	
	public String getlastName(){
		return lastName;
	}
}
