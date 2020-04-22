package ro.siit.servlet;

import ro.siit.model.Todo;
import ro.siit.model.User;
import ro.siit.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/todo.do")
public class TodoActionsServlet extends HttpServlet {


   private final TodoService todoService = new TodoService();

   @Override
   public void init () throws ServletException {
      super.init();
   }

   @Override
   protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String action = request.getParameter("action");
      action = (null == action) ? "list" : action;
      switch (action) {
         case "delete":
            UUID uuid = (UUID) (request.getSession().getAttribute("uuid"));
            try {
               List<Todo> todoList = todoService.retrieveTodos(uuid);
               for (Todo todo : todoList
               ) {
                  int idValueOfTodo = todo.getIdOfTodo();
                  todoService.deleteTodo(uuid, idValueOfTodo);
                  response.sendRedirect("todo.do");
                  return;
               }
            } catch (SQLException throwables) {
               throwables.printStackTrace();
            }
            break;
         case "add":
            request.getRequestDispatcher("/jsps/add.jsp").forward(request, response);
            break;
         case "edit":
            request.getRequestDispatcher("/jsps/edit.jsp").forward(request, response);
         default:
            uuid = (UUID) (request.getSession().getAttribute("uuid"));
            try {
               request.setAttribute("todos", todoService.retrieveTodos(uuid));
            } catch (SQLException throwables) {
               throwables.printStackTrace();
            }
            request.getRequestDispatcher("/jsps/todo.jsp").forward(request, response);
      }
   }

   @Override
   protected void doPost (HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
      String action = request.getParameter("action");
      action = (null == action) ? "list" : action;
      switch (action) {
         case "add":
            UUID uuid = (UUID) (request.getSession().getAttribute("uuid"));
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String date = request.getParameter("date");
            todoService.addTodoToDB(description, category, uuid, date);
            break;
         case "edit":
            uuid = (UUID) (request.getSession().getAttribute("uuid"));
            String newDescription = request.getParameter("description");
            String newCategory = request.getParameter("category");
            String newDate = request.getParameter("date");
            int idValueOfTodo = Integer.parseInt(request.getParameter("id"));
            todoService.editTodo(newDescription, newCategory, newDate, idValueOfTodo, uuid);
            break;
      }
      UUID uuid = (UUID) (request.getSession().getAttribute("uuid"));
      try {
         request.setAttribute("todos", todoService.retrieveTodos(uuid));
      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
      request.getRequestDispatcher("/jsps/todo.jsp").forward(request, response);
   }
}