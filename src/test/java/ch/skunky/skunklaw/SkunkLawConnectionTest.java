package ch.skunky.skunklaw;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SkunkLawConnectionTest {

    /**
     * https://www.baeldung.com/junit-5
     * https://dzone.com/articles/spring-boot-2-with-junit-5-and-mockito-2-for-unit
     *
     */

    @Ignore
    @Test
    @DisplayName("test MySQL connection on localhost.")
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
