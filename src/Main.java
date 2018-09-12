import Utils.dbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = dbConnector.createConnection();
        String removeCourseQuery = "DELETE FROM students_courses WHERE student_id = (SELECT student_id FROM students WHERE first_name=? AND course_id = (SELECT id FROM courses WHERE name=?);";
        String addCourseForStudentQuery = "insert into students_courses (student_id, course_id) values ((SELECT student_id FROM students WHERE first_name=?), ((SELECT id FROM courses WHERE name=?)))";
        String printCoursesQuery = "SELECT s.first_name, s.last_name, c.name FROM students_courses AS sc RIGHT JOIN students AS s ON sc.student_id = s.student_id RIGHT JOIN courses AS c ON sc.course_id = c.id";
        String addStudentQuery = "INSERT INTO students(student_id, faculty_id, first_name, last_name) VALUES(?, ?, ?, ?)";
        String addFacultyQuery = "INSERT INTO faculties(name) VALUES(?)";
        String removeStudentInFacultyQuery= "DELETE FROM students_faculties WHERE student_id = ? AND faculty_id = ?";
        String printAllStudentsInFaculty = "SELECT s.first_name, s.last_name, f.name FROM students_faculties AS sf RIGHT JOIN students AS s ON sf.student_id = s.student_id RIGHT JOIN faculties AS f ON sf.faculty_id = f.id";
        String addCourseQuery = "INSERT INTO courses (name, description, credits) VALUES('somecourse', ?, ?)";
        String removeFacultyQuery = "DELETE FROM faculties WHERE id = ?";


        PreparedStatement preparedStmt = connection.prepareStatement(addFacultyQuery);
        preparedStmt.setString(1, "fmi");
        preparedStmt.executeUpdate();

        preparedStmt.setString(1, "historical");
        preparedStmt.executeUpdate();

        preparedStmt.setString(1, "chemistry");
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addStudentQuery);
        preparedStmt.setInt(1, 1);
        preparedStmt.setInt(2, 1);
        preparedStmt.setString(3, "Panayot");
        preparedStmt.setString(4, "Marinov");
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addStudentQuery);
        preparedStmt.setInt(1, 2);
        preparedStmt.setInt(2, 3);
        preparedStmt.setString(3, "Ivancho");
        preparedStmt.setString(4, "Petrov");
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addStudentQuery);
        preparedStmt.setInt(1, 3);
        preparedStmt.setInt(2, 2);
        preparedStmt.setString(3, "Todor");
        preparedStmt.setString(1, "Radostiov");
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addStudentQuery);
        preparedStmt.setInt(1, 4);
        preparedStmt.setInt(2, 3);
        preparedStmt.setString(3, "Jivko");
        preparedStmt.setString(4, "Jivkov");
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseQuery);
        preparedStmt.setInt(1, 1);
        preparedStmt.setString(2, "Coding");
        preparedStmt.setString(3, "Java!");
        preparedStmt.setInt(4, 10);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseQuery);
        preparedStmt.setInt(1, 2);
        preparedStmt.setString(2, "Programming");
        preparedStmt.setString(3, "Python");
        preparedStmt.setInt(4, 8);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseQuery);
        preparedStmt.setInt(1, 3);
        preparedStmt.setString(2, "Other");
        preparedStmt.setString(3, "C#");
        preparedStmt.setInt(4, 6);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseForStudentQuery);
        preparedStmt.setInt(1, 2);
        preparedStmt.setInt(2, 2);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseForStudentQuery);
        preparedStmt.setInt(1, 2);
        preparedStmt.setInt(2, 1);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(addCourseForStudentQuery);
        preparedStmt.setInt(1, 3);
        preparedStmt.setInt(2, 2);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(removeFacultyQuery);
        preparedStmt.setInt(1, 1);
        preparedStmt.executeUpdate();

        preparedStmt = connection.prepareStatement(printCoursesQuery);
        preparedStmt.executeQuery();

        preparedStmt.close();
        connection.close();
    }
}
