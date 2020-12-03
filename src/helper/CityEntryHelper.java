/*   Created by IntelliJ IDEA.
 *   Author: Kshitij Varshney (kshitijvarshne1)
 *   Date: 03-Dec-20
 *   Time: 11:31 AM
 *   File: CityEntryHelper.java
 */
package helper;
import data.City;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityEntryHelper {
    //inserted query
    public static final String QUERY="Insert into cities values(?,?,?);";

    //Create
    public boolean addCityToDatabase(Connection connection,City city) throws SQLException {
        String name= city.getName();
        boolean traversal=city.isTraversed();
        int kilometres=city.getKilometres();
        PreparedStatement insertStatement=(
                connection.prepareStatement(QUERY)
        );
        insertStatement.setString(1, name);
        insertStatement.setBoolean(2, traversal);
        insertStatement.setInt(3, kilometres);
        insertStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "City added to the database.", "Added", JOptionPane.DEFAULT_OPTION);
        return true;

    }

    //Read
    public static final String Read_Query = "Select * from cities;";
    //Update
    public static final String UPDATE_QUERY = "Update cities set kilometres=? where name=?";
    //Delete
    public static final String DELETE_QUERY = "delete from cities where name=?;";

    public void readAllCitiesFromTheDatabase(Connection connection) {
        try {
            PreparedStatement readStatement = connection.prepareStatement(Read_Query);
            ResultSet str = readStatement.executeQuery();
            StringBuilder builder = new StringBuilder();
            while (str.next()) {
                String name = str.getString(1);
                boolean isTraversal = str.getBoolean(2);
                int kilometres = str.getInt(3);
                City city = new City(name, isTraversal, kilometres);
                builder.append(city.toString()).append("\n").append("-".repeat(120)).append("\n");
            }
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setLineWrap(true);
            scrollPane.setPreferredSize(new Dimension(512, 250));
            JOptionPane.showMessageDialog(
                    null,
                    scrollPane,
                    "Cities In The Database",
                    JOptionPane.PLAIN_MESSAGE
            );
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cant show the entries in the database", "Error", JOptionPane.PLAIN_MESSAGE
            );
        }
    }

    public void updateCity(Connection connection, String name, int kilometres) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_QUERY);

            updateStatement.setInt(1, kilometres);
            updateStatement.setString(2, name);
            updateStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "kilometre updated", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException throwables) {
            System.err.println(name + " is not there in the table.");
        }
    }

    public void deleteCity(Connection connection, String name) {
        try {
            PreparedStatement deleteStstement = connection.prepareStatement(DELETE_QUERY);
            deleteStstement.setNString(1, name);
            deleteStstement.executeUpdate();
            JOptionPane.showMessageDialog(null, "City deleted", null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}