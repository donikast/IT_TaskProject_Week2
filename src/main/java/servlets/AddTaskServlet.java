package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Task;
import repository.TaskRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tasks/add")
public class AddTaskServlet extends HttpServlet {
    private TaskRepository taskRepository;
    @Override
    public void init() throws ServletException {
        taskRepository = TaskRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       resp.setCharacterEncoding("UTF-8");
       PrintWriter pw = resp.getWriter();

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");

        if(title!=null && !title.isEmpty()
                && description!=null && !description.isEmpty()
                && deadline!=null && !deadline.isEmpty()) {
            Task task = new Task(title, description, deadline);
            taskRepository.addTask(task);

            pw.println("<html><body><p>");
            pw.println("Successfully added!");
            pw.println("</p></body></html>");
            resp.setStatus(201);
        }

        else {
            pw.println("<html><body><p>");
            pw.println("Not enough parameters!");
            pw.println("<p><body><html>");
            resp.setStatus(400);
        }
        pw.close();
    }
}

