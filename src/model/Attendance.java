package model;

import java.time.LocalDate;

public class Attendance {

    private int studentId;
    private int courseId;
    private LocalDate attendanceDate;
    private String status; // Present / Absent

    public Attendance(int studentId, int courseId, LocalDate attendanceDate, String status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public String getStatus() {
        return status;
    }
}