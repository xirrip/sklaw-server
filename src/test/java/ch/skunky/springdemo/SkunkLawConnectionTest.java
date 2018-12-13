package ch.skunky.springdemo;

import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SkunkLawConnectionTest {

    @Ignore
    @Test
    public void testConnection(){
        String url =
                "jdbc:mysql://localhost:3306/skunklaw";
        String username = "skunk";
        String password = "knuks"; //Root password set during MySQL installation procedure as described above.

        System.out.println("Connecting database...");

        try {
            Connection connection =
                    DriverManager.getConnection(url, username,
                            password);
            System.out.println("Database connected!");
        }
        catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    }

}
