package ro.siit.user_actions;

import ro.siit.login.CredentialsValidator;
import ro.siit.model.User;
import ro.siit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@WebServlet(urlPatterns = "/adduser")
public class AddUserServlet extends HttpServlet {


   User user;
   private final CredentialsValidator credentialsValidator = new CredentialsValidator();
   private final UserService userService = new UserService();

   @Override
   public void init () throws ServletException {
      super.init();
   }

   @Override
   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/jsps/regform.jsp").forward(request, response);

   }

   @Override
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
      String email = request.getParameter("Email");
      String pwd = request.getParameter("Password");
      String pwdCheckIfIdentical = request.getParameter("ConfirmPassword");

      if (credentialsValidator != null) {
         request.getRequestDispatcher("/jsps/loginpage.jsp").forward(request, response);
         request.setAttribute("error", "Username/password already taken. Please try logging in.");
      } else if (pwd == pwdCheckIfIdentical) {
         user = new User(UUID.randomUUID(), email, pwd);
         userService.addUser(user);
         request.getRequestDispatcher("/jsps/loginpage.jsp").forward(request, response);
         request.setAttribute("error", "Account created. Please log in.");
      }
   }
}
