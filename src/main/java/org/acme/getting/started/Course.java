package org.acme.getting.started;

public class Course {
    private Integer id;
    private  String course_title;

    public Course() {
    }

    public Course(Integer id, String course_title) {
        this.id = id;
        this.course_title = course_title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }


}
