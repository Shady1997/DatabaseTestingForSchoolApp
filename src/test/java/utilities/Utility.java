package utilities;

import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Random;

public class Utility {
    static String dbName;
    static String port;
    static ResultSet rs;
    static String userName;
    static String Password;
    public static Connection con;
    public static Statement stmt;

    // declare constructor
    public Utility(String dbName, String port, String userName, String Password) {
        Utility.dbName = dbName;
        Utility.userName = userName;
        Utility.Password = Password;
        Utility.port = port;
    }

    // start connect to db
    public static void createDBConnection(String dbName, String port, String userName, String Password) throws SQLException {
        Utility.dbName = dbName;
        Utility.userName = userName;
        Utility.Password = Password;
        Utility.port = port;
        con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + dbName, userName,
                Password);
    }

    // create statement
    public static void createStatement() throws SQLException {
        stmt = con.createStatement();
    }

    // get resultset from database
    public static ResultSet dbResultSet(String query) throws SQLException {

        rs = stmt.executeQuery(query);
//			while (rs.next())
//				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
//			con.close();
        return rs;
    }

    // close db connection
    public static void closedbConnection() throws SQLException {
        con.close();
    }
    // generate random string for data driven test
    public static String generateString(int StringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(StringLength);
        for (int i = 0; i < StringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    // generate random character
    public static char generateCharacter() {

        return generateString(1).charAt(0);
    }

    // generate random integer number
    public static int generateInteger(int upperBound) {
        Random rand = new Random(); // instance of random class
        // generate random values from 0-24
        return rand.nextInt(upperBound);
    }

    // generate random float number
    public static float generateFloat(int digitLength) {
        Random rand = new Random(); // instance of random class
        float float_random = rand.nextFloat();
        DecimalFormat df = new DecimalFormat(getDecimalFormat(digitLength));// format double digits
        return Float.parseFloat(df.format(float_random));
    }

    // generate random double number
    public static double generateDouble(int digitLength) {
        Random rand = new Random(); // instance of random class
        double double_random = rand.nextDouble();// generate double number
        DecimalFormat df = new DecimalFormat(getDecimalFormat(digitLength));// format double digits
        return Double.parseDouble(df.format(double_random));
    }

    // get decimal format
    public static String getDecimalFormat(int digitLength) {
        String doubleFormat = "#.";
        for (int i = 0; i < digitLength; i++) {
            doubleFormat += "#";
        }
        return doubleFormat;
    }
    // start html report
    public static void startHtmlReport(String reportDirName,String reportFileName) throws IOException {
        String path=System.getProperty("user.dir")+"/target/HtmlReports";
//        ProcessBuilder builder = new ProcessBuilder(
//                "cmd.exe", "/c", "cd \"C:\\Users\\eclipse-workspace\" && dir");
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd " + reportDirName + " && " + reportFileName);
        builder.redirectErrorStream(true);
        Process p = builder.start();
//        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        String line;
//        while (true) {
//            line = r.readLine();
//            if (line == null) { break; }
//            System.out.println(line);
//        }
    }
}
