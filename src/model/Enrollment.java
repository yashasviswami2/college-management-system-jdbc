package model;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private int semester;

    public Enrollment(int studentId, int courseId, int semester) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getSemester() {
        return semester;
    }
}