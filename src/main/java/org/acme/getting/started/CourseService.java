package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.agroal.api.AgroalDataSource;

@ApplicationScoped
public class CourseService {
    
    @Inject
    AgroalDataSource agr;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = agr.getConnection();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        // System.out.println("connection " + connection);
        return connection;
    }


    public ArrayList<Course> getAllCourses() {

        ArrayList<Course> list = new ArrayList<>();
        String sql = "select * from courses";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            // statement.setInt(1, id);
            ResultSet result = statement.executeQuery(sql);
            // int id, String course_title
            System.out.println("result" + result);
            while (result.next()) {
                list.add(new Course(result.getInt(1), result.getString(2)));
            }
        } catch (SQLException ae) {
            ae.printStackTrace();
        }
        return list;
    }

    public Course getCourse(Integer id) {
        System.out.println("id" + id);
        String sql = "SELECT * FROM courses WHERE id=?";
        Course course = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            System.out.println("TRY id:" + id);
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            // int id, String name
            System.out.println("result" + result);
            course = new Course();
            while (result.next()) {
                course.setId(result.getInt(1));
                course.setCourse_title(result.getString(2));
            }
        } catch (SQLException ae) {
            ae.printStackTrace();
        }
        return course;
    }

    public void updatedCourse(Course course){
        String sql = "UPDATE courses SET course_title = ? WHERE id=?";
        try {
            System.out.println("Course"+course.getCourse_title());
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(2, course.getId());
            statement.setString(1, course.getCourse_title());
            statement.executeUpdate();
        } catch (Exception ae) {
            ae.printStackTrace();
        }

    }

    public void setCourse(Course course) {

        String sql = "INSERT INTO courses (course_title) VALUES (?)";
        try {
            System.out.println("Course" + course.getCourse_title());
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, course.getCourse_title());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new course was inserted successfully!");
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        }

    }
}


