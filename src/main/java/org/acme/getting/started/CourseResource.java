package org.acme.getting.started;

//import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/courses")
public class CourseResource  {

    @Inject
    CourseService service;
//    public static ArrayList<Course> courses=new ArrayList<>();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello How Are you welcome to courses rest Curd Api !!";
    }

    
    @GET
    @Path("/AllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses(){
        return Response.ok(service.getAllCourses()).build();
    }


    @GET
    @Path("/getCourseById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id){
        return Response.ok(service.getCourse(id)).build();
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatedPerson(Course course){
        service.updatedCourse(course);
        return Response.ok(service.getAllCourses()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setPerson(Course course){
        service.setCourse(course);
        return Response.ok(service.getAllCourses()).build();
    }

}