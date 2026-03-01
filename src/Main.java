import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        FeeDAO feeDAO = new FeeDAO();

        while (true) {

            System.out.println("\n===== College ERP System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Enroll Student");
            System.out.println("6. View Enrollments");
            System.out.println("7. Mark Attendance");
            System.out.println("8. View Attendance");
            System.out.println("9. Add Fee Record");
            System.out.println("10. View Fee Records");
            System.out.println("11. Student Count Per Department");
            System.out.println("12. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter ERP Number: ");
                    String erp = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    departmentDAO.viewAllDepartments();
                    System.out.print("Enter Department ID: ");
                    int deptId = sc.nextInt();

                    Student student = new Student(erp, name, email, phone, deptId);
                    studentDAO.addStudent(student);
                    break;

                case 2:
                    studentDAO.viewAllStudentsWithDepartment();
                    break;

                case 3:
                    System.out.print("Enter Course Name: ");
                    String courseName = sc.nextLine();

                    departmentDAO.viewAllDepartments();
                    System.out.print("Enter Department ID: ");
                    int courseDeptId = sc.nextInt();

                    Course course = new Course(courseName, courseDeptId);
                    courseDAO.addCourse(course);
                    break;

                case 4:
                    courseDAO.viewAllCourses();
                    break;

                case 5:
                    studentDAO.viewAllStudentsWithDepartment();
                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();

                    courseDAO.viewAllCourses();
                    System.out.print("Enter Course ID: ");
                    int courseId = sc.nextInt();

                    System.out.print("Enter Semester: ");
                    int semester = sc.nextInt();

                    Enrollment enrollment = new Enrollment(studentId, courseId, semester);
                    enrollmentDAO.enrollStudent(enrollment);
                    break;

                case 6:
                    enrollmentDAO.viewEnrollments();
                    break;

                case 7:
                    studentDAO.viewAllStudentsWithDepartment();
                    System.out.print("Enter Student ID: ");
                    int attStudentId = sc.nextInt();

                    courseDAO.viewAllCourses();
                    System.out.print("Enter Course ID: ");
                    int attCourseId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Status (Present/Absent): ");
                    String status = sc.nextLine();

                    Attendance attendance = new Attendance(
                            attStudentId,
                            attCourseId,
                            LocalDate.now(),
                            status
                    );

                    attendanceDAO.markAttendance(attendance);
                    break;

                case 8:
                    attendanceDAO.viewAttendance();
                    break;

                case 9:
                    studentDAO.viewAllStudentsWithDepartment();
                    System.out.print("Enter Student ID: ");
                    int feeStudentId = sc.nextInt();

                    System.out.print("Enter Total Amount: ");
                    double total = sc.nextDouble();

                    System.out.print("Enter Paid Amount: ");
                    double paid = sc.nextDouble();

                    double due = total - paid;

                    Fee fee = new Fee(
                            feeStudentId,
                            total,
                            paid,
                            due,
                            LocalDate.now()
                    );

                    feeDAO.addFee(fee);
                    break;

                case 10:
                    feeDAO.viewFees();
                    break;

                case 11:
                    studentDAO.getStudentCountPerDepartment();
                    break;

                case 12:
                    System.out.println("Exiting ERP system...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}