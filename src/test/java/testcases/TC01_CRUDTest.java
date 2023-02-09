package testcases;

import org.testng.annotations.Test;
import utilities.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC01_CRUDTest extends TestBase{

    // TODO: Test CRUD for students table
    @Test(priority = 1, description = "insert new row to students tables with valid data")
    public void TC01_TestInsertToStudentsTable() {
        try {
            // Insert a new record into the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "INSERT INTO students (first_name, last_name, date_of_birth, address, email) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, FIRSTNAME);
            statement.setString(2, LASTNAME);
            statement.setString(3, BIRTHDATE);
            statement.setString(4, ADDRESS);
            statement.setString(5, EMAIL);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 2, description = "Select inserted data before from students table")
    public void TC02_TestSelectFromStudentsTable() {
        try {
            // Select a record from the database
            PreparedStatement statement = Utility.con.prepareStatement("SELECT * FROM students WHERE email = ?");
            statement.setString(1, EMAIL);
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(FIRSTNAME, resultSet.getString("first_name"));
            assertEquals(LASTNAME, resultSet.getString("last_name"));
            assertEquals(BIRTHDATE, resultSet.getString("date_of_birth"));
            assertEquals(ADDRESS, resultSet.getString("address"));
            assertEquals(EMAIL, resultSet.getString("email"));
            STUDENT_ID=resultSet.getInt("student_id");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 3,description = "Check Update on students table")
    public void TC03_TestUpdateOnStudentsTable() {
        try {
            // Update a record in the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "UPDATE students SET first_name = ? WHERE email = ?");
            FIRSTNAME =faker.name().firstName();
            statement.setString(1, FIRSTNAME);
            statement.setString(2, EMAIL);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 11, description = "Check Delete on Students Table")
    public void TC11_TestDeleteOnStudentsTable() {
        try {
            // Delete a record from the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "DELETE FROM students WHERE email = ?");
            statement.setString(1, EMAIL);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // TODO: Test CRUD for classes table
    @Test(priority = 4, description = "insert new row to classes table with valid data")
    public void TC04_TestInsertToClassesTable() {
        try {
            // Insert a new record into the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "INSERT INTO classes (class_name, class_teacher) VALUES (?, ?)");
            statement.setString(1, CLASSNAME);
            statement.setString(2, CLASS_TEACHER);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 5, description = "Select inserted data before from classes table")
    public void TC05_TestSelectFromCLassesTable() {
        try {
            // Select a record from the database
            PreparedStatement statement = Utility.con.prepareStatement("SELECT * FROM classes WHERE class_name = ?");
            statement.setString(1, CLASSNAME);
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(CLASSNAME, resultSet.getString("class_name"));
            CLASS_ID=resultSet.getInt("class_id");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 6,description = "Check Update on classes table")
    public void TC06_TestUpdateOnClassesTable() {
        try {
            // Update a record in the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "UPDATE classes SET class_name = ? WHERE class_id = ?");
            CLASSNAME =faker.name().firstName();
            statement.setString(1, CLASSNAME);
            statement.setString(2, Integer.toString(CLASS_ID));
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 12, description = "Check Delete on Classes Table")
    public void TC12_TestDeleteOnClassesTable() {
        try {
            // Delete a record from the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "DELETE FROM classes WHERE class_id = ?");
            statement.setString(1, Integer.toString(CLASS_ID));
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // TODO: Test CRUD for enrolments table
    @Test(priority = 7, description = "insert new row to enrolments table with valid data")
    public void TC07_TestInsertToEnrolmentsTable() {
        try {
            // Insert a new record into the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "INSERT INTO enrolments (student_id, class_id, enrolment_date) VALUES (?, ?, ?)");
            statement.setString(1, Integer.toString(STUDENT_ID));
            statement.setString(2, Integer.toString(CLASS_ID));
            statement.setString(3, ENROLMENT_DATE);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 8, description = "Select inserted data before from enrolments table")
    public void TC08_TestSelectFromEnrolmentsTable() {
        try {
            // Select a record from the database
            PreparedStatement statement = Utility.con.prepareStatement("SELECT * FROM enrolments WHERE enrolment_date = ?");
            statement.setString(1, ENROLMENT_DATE);
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(ENROLMENT_DATE, resultSet.getString("enrolment_date"));
            ENROLMENT_ID=resultSet.getInt("enrolment_id");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 9,description = "Check Update on enrolments table")
    public void TC09_TestUpdateOnEnrolmentsTable() {
        try {
            // Update a record in the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "UPDATE enrolments SET enrolment_date = ? WHERE enrolment_id = ?");
            ENROLMENT_DATE="2009-09-09";
            statement.setString(1, ENROLMENT_DATE);
            statement.setString(2, Integer.toString(ENROLMENT_ID));
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test(priority = 10, description = "Check Delete on enrolments Table")
    public void TC10_TestDeleteOnEnrolmentsTable() {
        try {
            // Delete a record from the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "DELETE FROM enrolments WHERE enrolment_id = ?");
            statement.setString(1, Integer.toString(ENROLMENT_ID));
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
