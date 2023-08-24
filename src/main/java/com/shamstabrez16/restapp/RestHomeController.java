package com.shamstabrez16.restapp;


import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
public class RestHomeController {

    @GET
    @Produces("text/html")
    public Response getStartingPage() {
        String output =  "<!DOCTYPE html>\r\n"
        		+ "<html>\r\n"
        		+ "<head>\r\n"
        		+ "    <meta charset=\"UTF-8\">\r\n"
        		+ "    <title>Student Management System</title>\r\n"
        		+ "</head>\r\n"
        		+ "<body>\r\n"
        		+ "    <h1>Welcome to Student Management System</h1>\r\n"
        		+ "    <p>This is a simple web application for managing student records.</p>\r\n"
        		+ "    <p><a href=\"students\">View Students</a></p>\r\n"
        		+ "</body>\r\n"
        		+ "</html>";
        return Response.status(200).entity(output).build();
    }
}

