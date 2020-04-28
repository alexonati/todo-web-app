package ro.siit.login;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.siit.model.User;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.UUID;


public class CredentialsValidator {


   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   static final Logger logger = LogManager.getLogger(CredentialsValidator.class);

//   private Connection connection;

//   public CredentialsValidator () {
//      try {
//         Class.forName("org.postgresql.Driver");
//         connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siit10?user=postgres&password=postgres");
//      } catch (ClassNotFoundException | SQLException e) {
//         e.printStackTrace();
//      }
//   }


   private Connection getConnection () throws URISyntaxException, SQLException {
      String dbUrl = System.getenv("JDBC_DATABASE_URL");
      return DriverManager.getConnection(dbUrl);
   }

   public User checkCredentials (String email, String password) {


      try {
         PreparedStatement ps = getConnection().prepareStatement("SELECT id, email, pwd FROM login WHERE email = ?");
         ps.setString(1, email);

         ResultSet rs = ps.executeQuery();

         if (rs.next() && passwordEncoder.matches(password, rs.getString("pwd"))) {
            return new User(UUID.fromString(String.valueOf(rs.getObject(1))),
                    rs.getString(2), rs.getString(3));
         } else {
            logger.log(Level.TRACE, "Reached else in credentials validator");
            return null;
         }
      } catch (SQLException | URISyntaxException e) {
         e.printStackTrace();
      }
      return null;
   }
}