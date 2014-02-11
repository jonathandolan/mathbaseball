import java.util.Random;
import java.util.Scanner;

public class MathBaseball {
	static Random generator = new Random();
	static Scanner scanner = new Scanner(System.in);
	static Student student = new Student();

	public static boolean generateQuestion(int max, int type){
		int a = generator.nextInt(max);
		int b = generator.nextInt(max);
		int answer;
		int input;
		if (type == 1){ // addition
			System.out.println(a + " + " + b + " = ");
			input = scanner.nextInt();
			answer = a + b;
			if (answer == input){
				//write to database
				student.advanceBases("bob");
				return true;
			}
			else{
				return false;
			}
		}
		else{ // subtraction
			System.out.println(a + " - " + b + " = ");
			input = scanner.nextInt();
			answer = a - b;
			if (answer == input){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("Please type the maximum number for questions.");
		int max = scanner.nextInt();
		while (true){
			
			System.out.println("Please type the type of question. 1 for addition, 2 for subtraction");
			int type = scanner.nextInt();
			boolean correct = MathBaseball.generateQuestion(max, type);
			if (correct == true){
				System.out.println("Correct!");
			}
			else{
				System.out.println("Incorrect!");
			}
		}
	}

}
