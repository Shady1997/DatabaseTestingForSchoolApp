package testcases;

import org.testng.annotations.Test;
import utilities.Utility;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TC02_ConstraintsTest extends TestBase{
    @Test
    public void testUniqueConstraint() {
        try {
            // insert a duplicate value into a column with a unique constraint
            insertDuplicateValue();
            fail("Expected SQLException for unique constraint violation");
        } catch (SQLException e) {
            assertEquals(23505, e.getErrorCode(),"expected error code for unique constraint violation");
        }
    }

    @Test
    public void testNotNullConstraint() {
        try {
            // insert a null value into a column with a not-null constraint
            insertNullValue();
            fail("Expected SQLException for not-null constraint violation");
        } catch (SQLException e) {
            assertEquals(23502, e.getErrorCode(),"expected error code for not-null constraint violation");
        }
    }
    @Test
    public void testPrimaryKeyConstraint() {
        try {
            // insert a duplicate primary key
            insertDuplicatePrimaryKey();
            fail("Expected SQLException for primary key violation");
        } catch (SQLException e) {
            assertEquals(23505, e.getErrorCode(),"expected error code for primary key violation");
        }
    }

    @Test
    public void testForeignKeyConstraint() {
        try {
            // insert a value into a foreign key column that does not match any primary key in the referenced table
            insertInvalidForeignKey();
            fail("Expected SQLException for foreign key violation");
        } catch (SQLException e) {
            assertEquals(23503, e.getErrorCode(),"expected error code for foreign key violation");
        }
    }

    private void insertDuplicateValue() throws SQLException {
        // insert code to insert a duplicate value into the database here
        PreparedStatement statement = Utility.con.prepareStatement(
                "INSERT INTO users (ID, username, password) VALUES (?, ?, ?)");
        statement.setString(1, "3");
        statement.setString(2, "shady");
        statement.setString(3, "shady");
    }

    private void insertNullValue() throws SQLException {
        // insert code to insert a null value into the database here
        PreparedStatement statement = Utility.con.prepareStatement(
                "INSERT INTO users (ID, username, password) VALUES (?, ?, ?)");
        statement.setString(2, "");
        statement.setString(3, "shady");

    }

    private void insertDuplicatePrimaryKey() throws SQLException {
        // insert code to insert a duplicate primary key into the database here
        PreparedStatement statement = Utility.con.prepareStatement(
                "INSERT INTO users (ID, username, password) VALUES (?, ?, ?)");
        statement.setString(1, "3");
        statement.setString(2, "shady");
        statement.setString(3, "shady");
    }

    private void insertInvalidForeignKey() throws SQLException {
        // insert code to insert an invalid foreign key into the database here
    }
}
