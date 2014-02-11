
public class Student{
	int score;
	int questionsRight;
	int questionsAttempted;
	String userName;
	String passWord;
	String[] diamond = {"", "1", "2", "3",}; //baseball diamond 0 = home 1 = 1st, etc.
	
	public void advanceBases(String batter){
		String temp;
		String temp2;
		temp = diamond[1];
		diamond[1] = batter;
		diamond[0] = diamond[3];
		diamond[3] = diamond[2];
		diamond[2] = temp;
		if (diamond[0] != ""){
			System.out.println("Score!");
			score++;
		}
		System.out.println(diamond);
		
	}
}
