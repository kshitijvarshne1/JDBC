/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 03-Dec-20
 *   Time: 11:04 AM
 *   File: DatabaseConnection.java
 */

package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL =
            "jdbc:mysql://localhost:3306/transport";

    public static final String USERNAME="root";
    public static final String PASSWORD="";
    public static Connection connection=null;

    public Connection connectToDatabase() {
        try {
            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
        } catch (SQLException throwables) {
            System.out.println("Cannot connect to the database ," + "Please check your credentials of database");
        } finally {
            printStatus();
        }
        return null;
    }


    private void printStatus() {
        System.out.println(connection==null ?"Database is inactive ":"Database is active.");
    }

    public static Connection getConnection() {
        return connection;
    }
}