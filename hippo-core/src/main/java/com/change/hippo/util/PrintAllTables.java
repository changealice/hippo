package com.change.hippo.util;

import org.junit.Test;

import java.sql.*;

public class PrintAllTables {

    @Test
    public void printAllTable() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bootdo", "root", "")) {
            final DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet rs = metaData.getTables(null, null, "%", null)) {
                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
                while (rs.next()) {
                    System.out.println("\n----------");
                    System.out.println(rs.getString("TABLE_NAME"));
                    System.out.println("----------");

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsMeta.getColumnName(i);
                        System.out.format("%s:%s\n", columnName, rs.getString(i));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
