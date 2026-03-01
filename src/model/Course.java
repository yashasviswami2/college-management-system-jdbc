package model;

public class Course {

    private int courseId;
    private String courseName;
    private int departmentId;

    public Course(String courseName, int departmentId) {
        this.courseName = courseName;
        this.departmentId = departmentId;
    }

    public Course(int courseId, String courseName, int departmentId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.departmentId = departmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}