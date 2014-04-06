//Main backend class for the Math Baseball program.
//written by: Jonathan Dolan
//Spring 2014

package MathBaseball.math_bb2;

import java.awt.Color;
//import java.sql.SQLException;
import java.util.Random;

public class MathBaseball{
    static Random generator = new Random();
    static Student student = new Student();
    static PlayBall gui;
    static int answer;
    static int player;
    static DBWrapper dataBase;
    static Team t;
    static int level;
    
    //Changes team template in the Team class
    public static void changeTemplate(String userName, int template){
    	t = new Team(template);
    	dataBase.changeTeamTemplate(userName, template);
    }
    
    //Gets an array of strings that represents location of current players
    public static String[] getDiamond(){
    	return Team.getCorrectDiamond();
    }
    
    //sets team name
    public static void setName(String name){
    	Team.setTeamName(name);
    }
    
    //gets team name
    public static String getName(){
    	return Team.teamName;
    }
    
    //gets color, set by template
   public static Color getColor(){
    	return t.getColor();
    }
   
   //gets total score
   public static int getScore(){
	   return Team.totalScore;
   }

    public static void makeGui(char type, DBWrapper db){
        System.out.println(type);
        gui = new PlayBall(type,db);
        gui.beginGame();
        dataBase = db;
        t = new Team(0);
    }

    //checks answer and displays, then saves stats to database
    public static void answerReceived(int input, char type){
        if (input == answer){
            gui.displayCorrect();
            dataBase.correctAnswer(player);
            t.advanceBases(level);
        }
        else{
            if (type == 'a'){
                gui.displayWrongAdd(answer);
                dataBase.incorrectAnswer(player);
            }
            else if (type == 's'){
                gui.displayWrongSub(answer);
                dataBase.incorrectAnswer(player);
            }
            else{
                gui.displayWrongPlaces(answer);
                dataBase.incorrectAnswer(player);
            }
        }
    }

    //generates a question
    public static void generateQuestion(int max, int type){
        int a = generator.nextInt(max);
        int b = generator.nextInt(max);
        

        if (type == 1){ // addition
            gui.displayAdditionQuestion(a, b);

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

            answer = a - b;

        }
        else{
            MathBaseball.generatePlaceQuestion();
        }
        //find level, to see how man bases are run
        if (answer <= 10){
        	level = 1;
        }
        else if (answer <= 100){
        	level = 2;
        }
        else
        	level = 3;
    }

    //generates place question
    public static void generatePlaceQuestion(){
    	level = 1;
        int given = generator.nextInt(900);
        given = given + 100;
        int place = generator.nextInt(2);
        place++;
        String intStr = Integer.toString(given);
        //make sure each place is unique
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

        if (place == 1){
            gui.displayPlacesQuestion(given, place);
            answer =  intStr.charAt(0);

        }
        else if (place == 2){
            gui.displayPlacesQuestion(given, place);
            answer= intStr.charAt(1);

        }
        else{
            gui.displayPlacesQuestion(given, place);
            answer = intStr.charAt(2);

        }




    }

}