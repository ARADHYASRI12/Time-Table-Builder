CREATE DATABASE timetable_db;

USE timetable_db;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  username VARCHAR(50) UNIQUE,
  password VARCHAR(100),
  email VARCHAR(100),
  department VARCHAR(100),
  role ENUM('Admin', 'Teacher', 'Student')
);

CREATE TABLE classrooms (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) UNIQUE,
  capacity INT,
  av_support BOOLEAN,
  num_computers INT
);

CREATE TABLE courses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  code VARCHAR(20) UNIQUE,
  name VARCHAR(100),
  credits INT,
  instructor_username VARCHAR(50),
  student_count INT,
  FOREIGN KEY (instructor_username) REFERENCES users(username)
);

CREATE TABLE timetable (
  id INT AUTO_INCREMENT PRIMARY KEY,
  course_code VARCHAR(20),
  instructor_username VARCHAR(50),
  classroom_name VARCHAR(100),
  day VARCHAR(10),
  period INT,
  FOREIGN KEY (course_code) REFERENCES courses(code),
  FOREIGN KEY (instructor_username) REFERENCES users(username),
  FOREIGN KEY (classroom_name) REFERENCES classrooms(name)
);


-- Insert users
INSERT INTO users (name, username, password, email, department, role) VALUES
('Ojus Kadu', 'ojus', 'password', 'ojus@example.com', 'Computer Science', 'Admin'),
('Abhijit Das', 'abhijitdas', 'abhijitdas', 'das@example.com', 'Computer Science', 'Teacher'),
('Carol Davis', 'carold', 'password3', 'carol@example.com', 'Electrical Engineering', 'Teacher'),
('David Miller', 'davidm', 'password4', 'david@example.com', 'Mechanical Engineering', 'Teacher'),
('Eva Thomas', 'evat', 'password5', 'eva@example.com', 'Computer Science', 'Student'),
('Frank White', 'frankw', 'password6', 'frank@example.com', 'Electrical Engineering', 'Student'),
('Grace Lee', 'gracel', 'password7', 'grace@example.com', 'Mechanical Engineering', 'Student');

-- Insert classrooms
INSERT INTO classrooms (name, capacity, av_support, num_computers) VALUES
('F101', 40, FALSE, 0),
('G202', 30, FALSE, 0),
('C303', 25, TRUE, 25),
('D404', 50, TRUE, 10),
('E505', 35, FALSE, 0);

-- Insert courses
INSERT INTO courses (code, name, credits, instructor_username, student_count) VALUES
('CS F101', 'Computer Programming', 4, 'abhijitdas', 60),
('EEE F201', 'Circuit Analysis', 3, 'carold', 45),
('ME F301', 'Thermodynamics', 3, 'davidm', 50),
('CS F202', 'Data Structures', 4, 'abhijitdas', 55),
('EEE F302', 'Digital Systems', 3, 'carold', 40);



