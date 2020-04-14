package ro.siit.servlet;

import ro.siit.model.Todo;
import ro.siit.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete.do")
public class DeleteTodoServlet extends HttpServlet {

   private TodoService todoService = new TodoService();

   @Override
   protected void doGet (HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
      todoService.deleteTodo(new Todo(request.getParameter("todo"), request.getParameter("category")));
      response.sendRedirect("listtodo.do");
   }
}