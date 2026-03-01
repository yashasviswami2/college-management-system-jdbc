-- ===============================
-- College Management System Database
-- ===============================

CREATE DATABASE IF NOT EXISTS college_management;
USE college_management;

-- ===============================
-- COURSE TABLE
-- ===============================
CREATE TABLE course (
                        course_id INT PRIMARY KEY AUTO_INCREMENT,
                        course_name VARCHAR(100) NOT NULL,
                        duration_years INT NOT NULL,
                        total_fees DECIMAL(10,2) NOT NULL
);

-- ===============================
-- STUDENT TABLE
-- ===============================
CREATE TABLE student (
                         student_id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         gender VARCHAR(10),
                         dob DATE,
                         phone VARCHAR(15) UNIQUE,
                         email VARCHAR(100) UNIQUE,
                         address TEXT,
                         course_id INT,
                         admission_date DATE,

                         FOREIGN KEY (course_id) REFERENCES course(course_id)
                             ON DELETE SET NULL
                             ON UPDATE CASCADE
);

-- ===============================
-- TEACHER TABLE
-- ===============================
CREATE TABLE teacher (
                         teacher_id INT PRIMARY KEY AUTO_INCREMENT,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         department VARCHAR(100),
                         phone VARCHAR(15) UNIQUE,
                         email VARCHAR(100) UNIQUE,
                         hire_date DATE,
                         salary DECIMAL(10,2)
);

-- ===============================
-- SUBJECT TABLE
-- ===============================
CREATE TABLE subject (
                         subject_id INT PRIMARY KEY AUTO_INCREMENT,
                         subject_name VARCHAR(100) NOT NULL,
                         course_id INT,
                         teacher_id INT,

                         FOREIGN KEY (course_id) REFERENCES course(course_id)
                             ON DELETE CASCADE,
                         FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id)
                             ON DELETE SET NULL
);

-- ===============================
-- MARKS TABLE
-- ===============================
CREATE TABLE marks (
                       marks_id INT PRIMARY KEY AUTO_INCREMENT,
                       student_id INT,
                       subject_id INT,
                       exam_type VARCHAR(50),
                       marks_obtained INT CHECK (marks_obtained BETWEEN 0 AND 100),

                       FOREIGN KEY (student_id) REFERENCES student(student_id)
                           ON DELETE CASCADE,
                       FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
                           ON DELETE CASCADE
);

-- ===============================
-- ATTENDANCE TABLE
-- ===============================
CREATE TABLE attendance (
                            attendance_id INT PRIMARY KEY AUTO_INCREMENT,
                            student_id INT,
                            subject_id INT,
                            attendance_date DATE,
                            status ENUM('Present','Absent') NOT NULL,

                            FOREIGN KEY (student_id) REFERENCES student(student_id)
                                ON DELETE CASCADE,
                            FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
                                ON DELETE CASCADE
);

-- ===============================
-- FEES TABLE
-- ===============================
CREATE TABLE fees (
                      fee_id INT PRIMARY KEY AUTO_INCREMENT,
                      student_id INT,
                      amount_paid DECIMAL(10,2),
                      payment_date DATE,
                      payment_mode VARCHAR(50),
                      status ENUM('Paid','Pending') NOT NULL,

                      FOREIGN KEY (student_id) REFERENCES student(student_id)
                          ON DELETE CASCADE
);

-- ===============================
-- SAMPLE DATA
-- ===============================

INSERT INTO course (course_name, duration_years, total_fees)
VALUES
    ('B.Tech Computer Science', 4, 400000),
    ('BBA', 3, 250000);

INSERT INTO teacher (first_name, last_name, department, phone, email, hire_date, salary)
VALUES
    ('Amit', 'Sharma', 'Computer Science', '9876543210', 'amit@college.com', '2020-06-01', 60000),
    ('Raghav', 'Verma', 'Management', '9876501234', 'raghav@college.com', '2019-04-10', 55000);

INSERT INTO student
(first_name, last_name, gender, dob, phone, email, address, course_id, admission_date)
VALUES
    ('Riya', 'Mehta', 'Male', '2004-05-12', '9123456789', 'riya@gmail.com', 'Delhi', 1, '2023-08-01'),
    ('Priya', 'sinha', 'Female', '2005-03-18', '9988776655', 'priya@gmail.com', 'Noida', 2, '2023-08-01');

INSERT INTO subject (subject_name, course_id, teacher_id)
VALUES
    ('Database Management System', 1, 1),
    ('Marketing Management', 2, 2);

INSERT INTO marks (student_id, subject_id, exam_type, marks_obtained)
VALUES
    (1, 1, 'Midterm', 85),
    (2, 2, 'Midterm', 78);

INSERT INTO fees (student_id, amount_paid, payment_date, payment_mode, status)
VALUES
    (1, 100000, '2026-01-10', 'Online', 'Paid'),
    (2, 50000, '2026-01-12', 'Cash', 'Pending');