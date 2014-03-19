package MathBaseball.math_bb2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWrapper {
    
    private final Connection connect;  // without auto-commit    
    
    public DBWrapper() throws SQLException {
        try { Class.forName("com.mysql.jdbc.Driver").newInstance(); }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException except) {
            throw new SQLException();
        }

        String serverName = "localhost";
        String mydatabase = "MathBaseball";
        String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 

        String username = "root";
        String password = "NEWPASS";
        
        connect = DriverManager.getConnection(url, username, password);
        connect.setAutoCommit(false);
    }
    
    public boolean close() {
        try {
            connect.close();
            return true;
        } catch (SQLException sqex) {
            return false;
        }
    }
    
    public void addNewtudent(String username, String password ,String firstName, String lastName, String teacherUserName) {
        PreparedStatement newData1 = null;
        PreparedStatement newData2 = null;
        PreparedStatement teacherIDFinder = null;
        int teacherID = -1;
        
        try {

        	newData1 = connect.prepareStatement(
                " insert into User (username, password, firstName, lastName, isAStudent) " + 
                " values (?, ?, ?, ?, ?); ");
            newData1.setString(1, username);
            newData1.setString(2, password);
            newData1.setString(3, firstName);
            newData1.setString(4, lastName);
            newData1.setInt(5, 1);
            newData1.executeUpdate();
            
            teacherIDFinder = connect.prepareStatement(
            		" select userID from User "
            		+ "where username = ?;");
            teacherIDFinder.setString(1, teacherUserName);
            ResultSet results = teacherIDFinder.executeQuery();
            if (results.next()) {
                teacherID = results.getInt(1);
            }
            
            
            newData2 = connect.prepareStatement(
                    " insert into Student (studentID, teacherID) " + 
                    " values (last_insert_id(), ?); ");
            newData2.setInt(1, teacherID);
            newData2.executeUpdate();
        
            connect.commit();
            
        } catch(SQLException sqex) {
        	try { connect.rollback(); System.err.println(sqex.toString()); }
            catch (SQLException sqex2) { System.err.println("Dammit\n" + sqex2.toString()); }
        } finally {
            if (newData1 != null) {
                try { newData1.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (newData2 != null) {
                try { newData2.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (teacherIDFinder != null) {
                try { teacherIDFinder.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }
    }
    
    public int startNewGame(String username) {
        PreparedStatement newData1 = null;
        PreparedStatement newData2 = null;
        PreparedStatement gameIDFinder = null;
        PreparedStatement studentIDFinder = null;
        int gameID = -1;
        int studentID = -1;
        
        try {

        	newData1 = connect.prepareStatement(
                " insert into GameScore (numberOfQuestions, numberOfCorrectAnswers) " + 
                " values (?, ?); ");
            newData1.setInt(1, 0);
            newData1.setInt(2, 0);
            newData1.executeUpdate();
            
            gameIDFinder = connect.prepareStatement(
            		" select last_insert_id(); ");
            ResultSet results = gameIDFinder.executeQuery();
            if (results.next()) {
                gameID = results.getInt(1);
            }
            
            studentIDFinder = connect.prepareStatement(
            		" select userID from User "
            		+ "where username = ? ;");
            studentIDFinder.setString(1, username);
            ResultSet result = studentIDFinder.executeQuery();
            if (result.next()) {
                studentID = result.getInt(1);
            }
            
            newData2 = connect.prepareStatement(
                    " insert into GameResult (gameID, studentID) " + 
                    " values (?, ?); ");
                newData2.setInt(1, gameID);
                newData2.setInt(2, studentID);
                newData2.executeUpdate();
        
            connect.commit();
            
        } catch(SQLException sqex) {
        	try { connect.rollback(); System.err.println(sqex.toString()); }
            catch (SQLException sqex2) { System.err.println("Dammit\n" + sqex2.toString()); }
        } finally {
            if (newData1 != null) {
                try { newData1.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (newData2 != null) {
                try { newData2.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (gameIDFinder != null) {
                try { gameIDFinder.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (studentIDFinder != null) {
                try { studentIDFinder.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }
        return gameID;
    }
    
    
    public void correctAnswer(int gameID) {
        PreparedStatement newData = null;
        
        try {
            newData = connect.prepareStatement(
                " update GameScore set numberOfQuestions = numberOfQuestions + 1, numberOfCorrectAnswers = numberOfCorrectAnswers + 1 " + 
                " where gameID = ? ; ");
            newData.setInt(1, gameID);
            newData.executeUpdate();
            connect.commit();
        
        } catch (SQLException sqex) {
            try { connect.rollback(); System.err.println(sqex.toString()); }
            catch (SQLException sqex2) { System.err.println("Dammit\n" + sqex2.toString()); }
        } finally {
            if (newData != null) {
                try { newData.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }        
    }
    
    
    public void incorrectAnswer(int gameID) {
        PreparedStatement newData = null;
        
        try {
            newData = connect.prepareStatement(
                " update GameScore set numberOfQuestions = numberOfQuestions + 1 " + 
                " where gameID = ? ; ");
            newData.setInt(1, gameID);
            newData.executeUpdate();
            connect.commit();
        
        } catch (SQLException sqex) {
            try { connect.rollback(); System.err.println(sqex.toString()); }
            catch (SQLException sqex2) { System.err.println("Dammit\n" + sqex2.toString()); }
        } finally {
            if (newData != null) {
                try { newData.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }        
    }
    
    
    public boolean login(String username, String password) {
        boolean result = true;
        PreparedStatement finder = null;
        
        try {
            
            finder = connect.prepareStatement(
                " select userID from User " +
                " where username = ? and password = ? ; ");
            finder.setString(1, username);
            finder.setString(2, password);
            
            ResultSet user = finder.executeQuery();
            if (!user.next()) {
            	result = false;
            }
            
        } catch (SQLException sqex) {
            System.err.println(sqex.toString());
        } finally {
            if (finder != null) {
                try { finder.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }
        
        return result;
    }
    
    
    
    
    public void addNewTeacher(String firstName, String lastName, String username, String password) {
        Statement select = null;
        PreparedStatement newData = null;
        
        try {
        	select = connect.createStatement();

        	newData = connect.prepareStatement(
                " insert into User (username, password, firstName, lastName, isAStudent) " + 
                " values (?, ?, ?, ?, ?); ");
            newData.setString(1, username);
            newData.setString(2, password);
            newData.setString(3, firstName);
            newData.setString(4, lastName);
            newData.setInt(5, 0);
            
            newData.executeUpdate();
            
            connect.commit();
        
        } catch(SQLException sqex) {
        	try { connect.rollback(); System.err.println(sqex.toString()); }
            catch (SQLException sqex2) { System.err.println("Dammit\n" + sqex2.toString()); }
        } finally {
        	if (select != null) {
                try { select.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
            if (newData != null) {
                try { newData.close(); }  catch (SQLException sqex) { System.err.println(sqex.toString()); }
            }
        }
    }
    
    
    public static void main(String[] aargh) throws SQLException {
        DBWrapper dbw = new DBWrapper();
        
        //System.out.println(dbw.startNewGame("cfowles"));
        //dbw.correctAnswer(6);
        //dbw.incorrectAnswer(6);
        
        dbw.close();
    }
    
}
