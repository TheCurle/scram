package uk.gemwire.scram.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.gemwire.scram.user.UserData;
import uk.gemwire.scram.user.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Manages accessing the database that stores all actual data related to the system.
 *
 * It contains:
 *  - Users; email, access, home folder
 *  - Servers; hostnames, packages, etc
 */
public class Database {

    public static final String LOCALHOST = "localhost";
    public static final String SCRAM_DB = "scram";
    public static final String TEST_USER = "test";
    public static final String TEST_USER_PASSWORD = "testuser";

    private static final String USER_STATEMENT = "SELECT ID, email, name, access, home FROM users";
    private static final String USER_LOGIN_STATEMENT = "SELECT ID, name, password_hash FROM users";

    public static Connection open(String hostname, String database, String username, String password) throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + hostname + "/" + database, username, password);
    }

    public static ResultSet getUserDetails(Connection conn) throws SQLException {
        return conn.createStatement().executeQuery(USER_STATEMENT);
    }

    public static ResultSet getUserLogins(Connection conn) throws SQLException {
        return conn.createStatement().executeQuery(USER_LOGIN_STATEMENT);
    }


    @Service
    public static class DatabaseUserService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserData user = userRepository.findByName(username);
            if (user == null)
                throw new UsernameNotFoundException("No user found with the username " + username);

            return new User(user.getName(), user.getHash(), true, true, true, true, new ArrayList<>());
        }
    }
}
