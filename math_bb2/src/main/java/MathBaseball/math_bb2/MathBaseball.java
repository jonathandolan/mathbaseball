package MathBaseball.math_bb2;

import java.util.Random;
import java.util.Scanner;

public class MathBaseball {
	static Random generator = new Random();
	static Scanner scanner = new Scanner(System.in);
	static Student student = new Student();
    static PlayBall gui;
	static int answer;

    public static void makeGui(char t){
        gui = new PlayBall(t);
        gui.beginGame();
    }
	
	public static void answerReceived(int input, char type){
		if (input == answer){
			gui.displayCorrect();
		}
		else{
			if (type == 'a'){
				gui.displayWrongAdd(answer);
			}
			else if (type == 's'){
				gui.displayWrongSub(answer);
			}
			else{
				gui.displayWrongPlaces(answer);
			}
		}
	}

	public static void generateQuestion(int max, int type){
		int a = generator.nextInt(max);
		int b = generator.nextInt(max);
	//	int answer;
		int input;
		if (type == 1){ // addition
			gui.displayAdditionQuestion(a, b);

		//	input = scanner.nextInt();
			answer = a + b;

			
	}
		else if (type == 2){ // subtraction
			if (a < b){
				int temp;
				temp = a;
				a = b;
				b = temp;
			}
			gui.displaySubtractionQuestion(a, b);
		//	System.out.println(a + " - " + b + " = ");
			//input = scanner.nextInt();
			answer = a - b;
			
		}
		else{
			 MathBaseball.generatePlaceQuestion();
		}
	}
	
	public static void generatePlaceQuestion(){
		int input;
		int given = generator.nextInt(900);
		given = given + 100;
		int place = generator.nextInt(2);
		place++;
		String intStr = Integer.toString(given);
		//make sure each place is unique
		//needed?
		while (true){
		if (intStr.charAt(0) == intStr.charAt(1)){
			intStr.replace(intStr.charAt(0), (Integer.toString(generator.nextInt(9)).charAt(0)));
		}
		else if(intStr.charAt(0) == intStr.charAt(2)){
			intStr.replace(intStr.charAt(0), (Integer.toString(generator.nextInt(9)).charAt(0)));
		}
		else if(intStr.charAt(1) == intStr.charAt(2)){
			intStr.replace(intStr.charAt(1), (Integer.toString(generator.nextInt(9)).charAt(0)));
		}
		else{
			break;
			}
		}
		
		given = Integer.parseInt(intStr);
		//print questions and check answer
		
		if (place == 1){
			gui.displayPlacesQuestion(given, place);
		//	System.out.println("What is in the 1's place of " + given + "?");
			input = scanner.nextInt();
			answer =  intStr.charAt(0);
			
		}
		else if (place == 2){
			gui.displayPlacesQuestion(given, place);
		//	System.out.println("What is in the 10's place of " + given + "?");
			input = scanner.nextInt();
			answer= intStr.charAt(1);
			
		}
		else{
			gui.displayPlacesQuestion(given, place);
			//System.out.println("What is in the 100's place of " + given + "?");
			input = scanner.nextInt();
			answer = intStr.charAt(2);
			
		}
		
		
		
		
	}
	
	
	/*public static void main(String[] args) {
		System.out.println("Please type the maximum number for questions.");
		int max = scanner.nextInt();
		while (true){
			System.out.println("Please type the type of question. 1 for addition, 2 for subtraction, 3 for places");
			int type = scanner.nextInt();
			MathBaseball.generateQuestion(max, type);
			
		}
	}*/

}
