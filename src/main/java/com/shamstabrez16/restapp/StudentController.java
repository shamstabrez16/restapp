package com.shamstabrez16.restapp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.shamstabrez16.restapp.service.StudentService;
import java.util.List;

@Path("/students")
public class StudentController {

	
	private List<Student> studentList;
 
    public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	private final StudentService service ;
    public StudentController() {
    	this.service = new StudentService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() throws ClassNotFoundException {
    	return service.getAllStudents();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") Long id) {
    	 Student student =service.getStudentById(id);
        return Response.ok(student).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
    	service.addStudent(student);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") Long id, Student updatedStudent) {
    	updatedStudent.setId(id);
    	Student student =service.getStudentById(id);
    	int n = service.updateStudent(updatedStudent);
    	if(n>0)
        return Response.ok(student.getFirstName()+" "+student.getLastName() +" updated").build();
        
        return Response.notModified().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
    	Student student =service.getStudentById(id);
    	int n = service.deleteStudent(id);
    	if(n>0)
        return Response.ok(student.getFirstName()+" "+student.getLastName() +" deleted").build();
    	
    	return Response.notModified().build();
    }
}
