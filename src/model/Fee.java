package model;

import java.time.LocalDate;

public class Fee {

    private int studentId;
    private double totalAmount;
    private double paidAmount;
    private double dueAmount;
    private LocalDate paymentDate;

    public Fee(int studentId, double totalAmount, double paidAmount, double dueAmount, LocalDate paymentDate) {
        this.studentId = studentId;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.dueAmount = dueAmount;
        this.paymentDate = paymentDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}