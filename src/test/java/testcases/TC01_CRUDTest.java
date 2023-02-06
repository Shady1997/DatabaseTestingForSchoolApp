package testcases;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import utilities.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TC01_CRUDTest extends TestBase{

    String USERNAME="testuser";
    String PASSWOR="testpass";
    @Test
    public void testInsert() {
        try {
            // Insert a new record into the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            statement.setString(1, USERNAME);
            statement.setString(2, PASSWOR);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
            // extend report status
            test.log(LogStatus.PASS, "Data Inserted Correctly to Database");
        }catch (SQLException e){
            // extend report status
            test.log(LogStatus.FAIL, "Failed to insert data to database");
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect() {
        try {
            // Select a record from the database
            PreparedStatement statement = Utility.con.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, USERNAME);
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(USERNAME, resultSet.getString("USERNAME"));
            assertEquals(PASSWOR, resultSet.getString("PASSWORD"));
            // extend report status
            test.log(LogStatus.PASS, "Select Inserted Data Correctly from Database");
        }catch (SQLException e){
            // extend report status
            test.log(LogStatus.FAIL, "Failed to select inserted data from database");
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            // Update a record in the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "UPDATE users SET password = ? WHERE username = ?");
            PASSWOR="newpass";
            statement.setString(1, PASSWOR);
            statement.setString(2, USERNAME);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
            // extend report status
            test.log(LogStatus.PASS, "Update Inserted Data Correctly in Database");
        }catch (SQLException e){
            // extend report status
            test.log(LogStatus.FAIL, "Failed to Update inserted data in database");
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            // Delete a record from the database
            PreparedStatement statement = Utility.con.prepareStatement(
                    "DELETE FROM users WHERE username = ?");
            statement.setString(1, USERNAME);
            int rowsAffected = statement.executeUpdate();
            assertEquals(1, rowsAffected);
            // extend report status
            test.log(LogStatus.PASS, "Delete Inserted Data Correctly from Database");
        }catch (SQLException e){
            // extend report status
            test.log(LogStatus.FAIL, "Failed to Delete inserted data from database");
            e.printStackTrace();
        }
    }

}
