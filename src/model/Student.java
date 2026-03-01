package model;

public class Student {

    private int studentId;
    private String erpNumber;
    private String name;
    private String email;
    private String phone;
    private int departmentId;

    public Student(String erpNumber, String name, String email, String phone, int departmentId) {
        this.erpNumber = erpNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getErpNumber() {
        return erpNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}