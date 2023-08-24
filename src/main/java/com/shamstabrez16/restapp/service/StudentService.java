package com.shamstabrez16.restapp.service;

import java.util.List;

import com.shamstabrez16.restapp.Student;
import com.shamstabrez16.restapp.dataaccess.*;
public class StudentService {
	public DataAccess dataAccess;
	
	public StudentService() {
		this.dataAccess = new DataAccess();
	}
	public List<Student> getAllStudents() throws ClassNotFoundException {
		return dataAccess.getAllStudents();
	}
	public Student getStudentById(Long id) {
		try {
			return dataAccess.getStudentById(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int addStudent(Student student) {
		return dataAccess.addStudent(student);
		
	}
	public int updateStudent(Student updatedStudent) {
		return dataAccess.updateStudent(updatedStudent);
	}
	public int deleteStudent(Long id) {
		return dataAccess.deleteStudent(id);
	}

}
