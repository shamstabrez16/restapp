package com.shamstabrez16.restapp.dataaccess;

import com.shamstabrez16.restapp.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataAccess {
	    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studentdb"; 
	    private static final String DB_USER = "postgres"; 
	    private static final String DB_PASSWORD = "sysadmin"; 
	    
	    
	    
	    public List<Student> getAllStudents() throws ClassNotFoundException {
	    	List<Student> studentList = new ArrayList<>();
	        try {
	        	Class.forName("org.postgresql.Driver");
		    	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		    	 Statement statement = connection.createStatement();

	            String query = "SELECT * FROM students";
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                Long id = resultSet.getLong("id");
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");
	                studentList.add(new Student(id, firstName, lastName));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return studentList;
	    }



		public Student getStudentById(Long id) throws ClassNotFoundException {
		
	        try {
	        	Class.forName("org.postgresql.Driver");
		    	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		    	 Statement statement = connection.createStatement();

		    	String query = "SELECT * FROM students WHERE id = " + id;
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                Long studentId = resultSet.getLong("id");
	                String firstName = resultSet.getString("first_name");
	                String lastName = resultSet.getString("last_name");
	                return new Student(studentId, firstName, lastName);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
		}



		public int addStudent(Student student) {
			try {
			    Class.forName("org.postgresql.Driver");
			    Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			    String insertQuery = "INSERT INTO students (first_name, last_name) VALUES (?, ?)";
			    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			    preparedStatement.setString(1, student.getFirstName());
			    preparedStatement.setString(2, student.getLastName());
			    
			    int rowsInserted = preparedStatement.executeUpdate();
			    if (rowsInserted > 0) {
			        System.out.println("New student inserted successfully.");
			    } else {
			        System.out.println("Failed to insert new student.");
			    }

			    connection.close();
			    return rowsInserted;
			} catch (SQLException | ClassNotFoundException e) {
			    e.printStackTrace();
			}

			return 0;
		}
		
		public int updateStudent(Student student) {
			try {
			    Class.forName("org.postgresql.Driver");
			    Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			    
			    String updateQuery = "UPDATE students SET first_name = ?, last_name = ? WHERE id = ?";
			    
			    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			    preparedStatement.setString(1, student.getFirstName());
			    preparedStatement.setString(2, student.getLastName());
			    preparedStatement.setLong(3, student.getId());
			   
			    int rowsUpdated = preparedStatement.executeUpdate();
			    if (rowsUpdated > 0) {
			        System.out.println("Student information updated successfully.");
			    } else {
			        System.out.println("Failed to update student information.");
			    }

			    connection.close();
			    return rowsUpdated;
			} catch (SQLException | ClassNotFoundException e) {
			    e.printStackTrace();
			}
			return 0;

		}
		
		public int deleteStudent(Long id) {
			try {
			    Class.forName("org.postgresql.Driver");
			    Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			    String deleteQuery = "DELETE FROM students WHERE id = ?";
			    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
			    preparedStatement.setLong(1, id);
			    int rowsDeleted = preparedStatement.executeUpdate();
			    if (rowsDeleted > 0) {
			        System.out.println("Student deleted successfully.");
			    } else {
			        System.out.println("Failed to delete student.");
			    }

			    connection.close();
			    return rowsDeleted;
			} catch (SQLException | ClassNotFoundException e) {
			    e.printStackTrace();
			}
			return 0;
		}
}
